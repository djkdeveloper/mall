package com.djk.authority;

import com.djk.utils.IteratorUtils;
import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/9.
 * 权限接口实现
 */
@Slf4j
@Service
public class AuthorityServiceImpl implements AuthorityService {


    /**
     * 注入权限数据库接口
     */
    @Autowired
    private AuthorityMapper authorityMapper;

    @Log
    @Override
    public List<Authority> queryManagerAuthoritys(long managerId) {
        return authorityMapper.queryManagerAuthoritys(managerId);
    }

    @Log
    @Override
    public Authority queryManagerAuthoritysInduction(long managerId) {
        // 查询用户的所有权限
        List<Authority> authorityList = this.queryManagerAuthoritys(managerId);
        if (CollectionUtils.isEmpty(authorityList)) {
            return Authority.buildRootNode();
        }
        return getManagerAuthoritysInduction(authorityList);
    }

    @Log
    @Override
    public List<Authority> queryByRoleId(long roleId) {
        return authorityMapper.queryByRoleId(roleId);
    }

    /**
     * 获得父子节点归纳好的权限
     *
     * @param authorities 权限
     * @return 返回权限信息
     */
    private Authority getManagerAuthoritysInduction(List<Authority> authorities) {
        // 根节点
        Authority authority = Authority.buildRootNode();

        // 过滤出一级节点
        List<Authority> firstAuthoritys = authorities.stream().filter(Authority::isFirstNode).collect(Collectors.toList());

        //  过滤出二级节点
        List<Authority> secondAuthoritys = authorities.stream().filter(Authority::isSecondNode).collect(Collectors.toList());

        // 过滤出三级节点
        List<Authority> thirdAuthoritys = authorities.stream().filter(Authority::isThirdNode).collect(Collectors.toList());

        // 过滤出四级节点
        List<Authority> fourthAuthoritys = authorities.stream().filter(Authority::isFourthNode).collect(Collectors.toList());

        // 设置根节点的子节点
        authority.setChildren(firstAuthoritys);

        // 这边避开递归

        // 设置一级节点的子节点
        if (!CollectionUtils.isEmpty(firstAuthoritys) && !CollectionUtils.isEmpty(secondAuthoritys)) {
            IteratorUtils.zip(firstAuthoritys, secondAuthoritys, (authority1, authority2) -> authority1.getId() == authority2.getParentId(), (authority3, authority21) -> authority3.addChild(authority21));
        }

        // 设置二级节点的子节点
        if (!CollectionUtils.isEmpty(secondAuthoritys) && !CollectionUtils.isEmpty(thirdAuthoritys)) {
            IteratorUtils.zip(secondAuthoritys, thirdAuthoritys, (authority1, authority2) -> authority1.getId() == authority2.getParentId(), (authority3, authority21) -> authority3.addChild(authority21));
        }

        // 设置三级节点的子节点
        if (!CollectionUtils.isEmpty(thirdAuthoritys) && !CollectionUtils.isEmpty(fourthAuthoritys)) {
            IteratorUtils.zip(thirdAuthoritys, fourthAuthoritys, (authority1, authority2) -> authority1.getId() == authority2.getParentId(), (authority3, authority21) -> authority3.addChild(authority21));
        }

        return authority;
    }
}
