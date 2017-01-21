package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("recordService")
public class RecordService implements ListService,DeleteService,ExecuteService,UpdateService {
    @Autowired
    @Qualifier("recordDao")
    private RecordDao recordDao;

    @Override
    public Object list(Object object) throws ServiceException {
        RecordQuery query = (RecordQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getMm_msg_type())) {
            map.put("mm_msg_type", query.getMm_msg_type());
        }

        if (!StringUtil.isNullOrEmpty(query.getKeyword())) {
            map.put("keyword", query.getKeyword());
        }

        List<RecordVO> lists = recordDao.listRecordVo(map);
        for (RecordVO record : lists){
            if (!StringUtil.isNullOrEmpty(record.getMm_emp_cover())){
                if (record.getMm_emp_cover().startsWith("upload")){
                    record.setMm_emp_cover(Constants.URL+record.getMm_emp_cover());
                }else {
                    record.setMm_emp_cover(Constants.QINIU_URL + record.getMm_emp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getMm_msg_picurl())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getMm_msg_picurl()!=null){
                    pics = record.getMm_msg_picurl().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                record.setMm_msg_picurl(buffer.toString());
            }
        }

        long count = recordDao.count(map);

        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_msg_id = (String) object;
        recordDao.deleteById(mm_msg_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        String mm_msg_id = (String) object;
        RecordVO recordVO = recordDao.findById(mm_msg_id);
        return recordVO;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof Record){
            Record record = (Record) object;
            recordDao.updateTop(record);
        }
        return null;
    }
}
