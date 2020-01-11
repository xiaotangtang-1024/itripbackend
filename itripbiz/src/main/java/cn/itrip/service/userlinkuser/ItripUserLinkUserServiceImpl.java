package cn.itrip.service.userlinkuser;

import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.dao.userlinkuser.ItripUserLinkUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItripUserLinkUserServiceImpl implements ItripUserLinkUserService {

    @Resource
    private ItripUserLinkUserMapper itripUserLinkUserMapper;

    @Override
    public Integer modifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
        itripUserLinkUser.setModifyDate(new Date());
        return itripUserLinkUserMapper.updateItripUserLinkUser(itripUserLinkUser);
    }

    @Override
    public Integer deleteItripUserLinkUserByIds(Long[] ids)throws Exception{
        return itripUserLinkUserMapper.deleteItripUserLinkUserByIds(ids);
    }

    @Override
    public Integer addItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
        itripUserLinkUser.setCreationDate(new Date());
        return itripUserLinkUserMapper.insertItripUserLinkUser(itripUserLinkUser);
    }

    @Override
    public List<ItripUserLinkUser> getItripUserLinkUserListByMap(Map<String,Object> param)throws Exception{
        List<ItripUserLinkUser> list = itripUserLinkUserMapper.getItripUserLinkUserListByMap(param);
        return list;
    }
}
