package com.bentest.gbf.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bentest.gbf.vo.GoodsMsVo;

@Mapper
public interface GoodsMapper {
	
	@Select("select gd.*,gdms.ms_price,gdms.stock_count,gdms.start_time,gdms.end_time "
		  + "from goods_ms gdms left join goods gd on gdms.goods_id=gd.id ")
	public List<GoodsMsVo> getGoodsMsVoList();
}
