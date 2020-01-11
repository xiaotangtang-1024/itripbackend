package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripHotelMapper {


    /**
     *  根据酒店ID获取商圈
     * @param id
     * @return
     * @throws Exception
     */
    public List<ItripAreaDic> getHotelAreaByHotelId(@Param(value = "id") Long id)throws Exception;
    // getItripHotelById 方法到本文档的其他位置进行查找。
    /**
     *  根据酒店ID获取特色
     * @param id 酒店ID
     */
    public List<ItripLabelDic> getHotelFeatureByHotelId(@Param(value = "id") Long id)throws Exception;

    public ItripSearchFacilitiesHotelVO getItripHotelFacilitiesById(@Param(value = "id") Long id) throws Exception;

    public ItripSearchPolicyHotelVO queryHotelPolicy(@Param(value = "id") Long id) throws Exception;

    public ItripHotel getItripHotelById(@Param(value = "id") Long id)throws Exception;

}
