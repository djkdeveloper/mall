package com.djk.spu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/12.
 * 商品数据库接口
 */
@Mapper
public interface SpuMapper {

    /**
     * 查询商品总数
     *
     * @param params 查询参数
     * @return 返回商品总数
     */
    int querySpuCount(Map<String, Object> params);

    /**
     * 查询商品信息
     *
     * @param params 查询参数
     * @return 返回商品相信
     */
    List<Spu> querySpus(Map<String, Object> params);

    /**
     * 添加商品
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    int addSpu(Spu spu);

    /**
     * 删除商品信息
     *
     * @param ids 商品id
     * @return 成功返回>0 失败返回0
     */
    int deleteSpus(Long[] ids);

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品信息
     */
    Spu queryById(long id);

    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    int updateSpu(Spu spu);
}
