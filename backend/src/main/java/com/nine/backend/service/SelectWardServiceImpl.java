package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.backend.mapper.WardInfoMapper;
import com.nine.backend.mapper.WardMapper;
import com.nine.backend.pojo.Ward;
import com.nine.backend.pojo.WardInfo;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SelectWardServiceImpl implements SelectWardService {
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private WardInfoMapper wardInfoMapper;


    @Override
    public Result showFirstWard(HttpServletRequest request) {
        //默认为内科
        Map<String,Object> map = new HashMap<>();
        String[] roomNumbers = {"1", "2", "3","4","5"};
        for (String roomNumber : roomNumbers) {
            QueryWrapper<Ward> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("room_number",roomNumber)
                    .eq("department_name","内科");
            Ward ward = wardMapper.selectOne(queryWrapper);
            if(ward.getPatientId() != null){
                map.put(roomNumber,1);
            }
            else map.put(roomNumber,0);
        }
        request.getSession().setAttribute("departmentName","内科");
        return getInfo(map);
    }

    @Override
    public Result showSpecWard(HttpServletRequest request) {
        String departmentName = request.getParameter("department");
        Map<String,Object> map = new HashMap<>();
        String[] roomNumbers = {"1", "2", "3","4","5"};
        for (String roomNumber : roomNumbers) {
            QueryWrapper<Ward> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("room_number",roomNumber)
                    .eq("department_name",departmentName);
            Ward ward = wardMapper.selectOne(queryWrapper);
            if(ward.getPatientId() != null){
                map.put(roomNumber,1);
            }
            else map.put(roomNumber,0);
        }
        request.getSession().setAttribute("departmentName",departmentName);
        return Result.success(map);
    }

    @Override
    public Result selectWard(HttpServletRequest req2) {
        Long patientId = Long.parseLong(req2.getSession().getAttribute("patientId").toString());
        String departmentName = req2.getSession().getAttribute("departmentName").toString();
        String roomNumber = req2.getParameter("roomNumber");
        QueryWrapper<Ward> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_number",roomNumber)
                .eq("department_name",departmentName);
        Ward ward = wardMapper.selectOne(queryWrapper);
        ward.setPatientId(patientId);
        wardMapper.updateById(ward);
        WardInfo wardInfo = wardInfoMapper.selectById(1L);
        wardInfo.setHistory(wardInfo.getHistory() + 1);
        wardInfo.setRemaining(wardInfo.getRemaining() - 1);
        wardInfoMapper.updateById(wardInfo);
        Map<String,Object> map = new HashMap<>();
        return getInfo(map);
    }

    @NotNull
    private Result getInfo(Map<String, Object> map) {
        WardInfo wardInfo = wardInfoMapper.selectOne(null);
        int remaining = wardInfo.getRemaining();
        int history = wardInfo.getHistory();
        double rate = ((25 - remaining) / 25.0) * 100;
        String rateStr = String.format("%.2f", rate) + "%";
        map.put("remaining",remaining);
        map.put("history",history);
        map.put("rate",rateStr);
        return Result.success(map);
    }

}
