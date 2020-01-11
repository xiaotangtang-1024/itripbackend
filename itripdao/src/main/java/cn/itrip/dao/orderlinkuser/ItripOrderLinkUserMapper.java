package cn.itrip.dao.orderlinkuser;

import cn.itrip.beans.pojo.ItripOrderLinkUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripOrderLinkUserMapper {

    public Integer deleteItripOrderLinkUserByOrderId(@Param(value = "orderId") Long orderId)throws Exception;

    public Integer insertItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

    public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception;
}
