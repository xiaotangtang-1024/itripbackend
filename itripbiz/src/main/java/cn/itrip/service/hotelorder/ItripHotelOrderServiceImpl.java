package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.pojo.ItripOrderLinkUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.common.BigDecimalUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.dao.hotelroom.ItripHotelRoomMapper;
import cn.itrip.dao.orderlinkuser.ItripOrderLinkUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_DOWN;

@Service
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService {

//    @Resource
//    private ItripHotelOrderMapper itripHotelOrderMapper;

    @Resource
    private ItripOrderLinkUserMapper itripOrderLinkUserMapper;

    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;


    public BigDecimal getOrderPayAmount(int count, Long roomId) throws Exception {
        BigDecimal payAmount = null;
        BigDecimal roomPrice = itripHotelRoomMapper.getItripHotelRoomById(roomId).getRoomPrice();
        payAmount = BigDecimalUtil.OperationASMD(count, roomPrice,
                BigDecimalUtil.BigDecimalOprations.multiply,
                2, ROUND_DOWN);
        return payAmount;
    }

    public Map<String, String> itriptxAddItripHotelOrder(ItripHotelOrder itripHotelOrder, List<ItripUserLinkUser> linkUserList) throws Exception {
        //定义变量map，里面存放订单的id和orderNo返回给前端
        Map<String, String> map = new HashMap<String, String>();
        if (null != itripHotelOrder) {
            int flag=0;
            if (EmptyUtils.isNotEmpty(itripHotelOrder.getId())) {
                //删除联系人
                itripOrderLinkUserMapper.deleteItripOrderLinkUserByOrderId(itripHotelOrder.getId());
                itripHotelOrder.setModifyDate(new Date());
                //flag=itripHotelOrderMapper.updateItripHotelOrder(itripHotelOrder);
            } else {
                itripHotelOrder.setCreationDate(new Date());
                //flag=itripHotelOrderMapper.insertItripHotelOrder(itripHotelOrder);
            }
            if (flag > 0) {
                Long orderId = itripHotelOrder.getId();
                //添加订单之后还需要往订单与常用联系人关联表中添加记录
                if (orderId > 0) {
                    for (ItripUserLinkUser itripUserLinkUser : linkUserList) {
                        ItripOrderLinkUser itripOrderLinkUser = new ItripOrderLinkUser();
                        itripOrderLinkUser.setOrderId(orderId);
                        itripOrderLinkUser.setLinkUserId(itripUserLinkUser.getId());
                        itripOrderLinkUser.setLinkUserName(itripUserLinkUser.getLinkUserName());
                        itripOrderLinkUser.setCreationDate(new Date());
                        itripOrderLinkUser.setCreatedBy(itripHotelOrder.getCreatedBy());
                        itripOrderLinkUserMapper.insertItripOrderLinkUser(itripOrderLinkUser);
                    }
                }
                map.put("id", itripHotelOrder.getId().toString());
                map.put("orderNo", itripHotelOrder.getOrderNo());
                return map;
            }
        }
        return map;
    }

}
