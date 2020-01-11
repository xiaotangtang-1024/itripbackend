package cn.itrip.service.orderlinkuser;

import java.util.List;

public interface ItripOrderLinkUserService {

    /**
     * 查询所有未支付的订单所关联的所有常用联系人
     * @return
     */
    public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception;


}
