package com.nine.backend;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.backend.mapper.*;
import com.nine.backend.pojo.*;
import com.nine.backend.service.RunningService;
import com.nine.backend.utils.Result;
import com.nine.backend.utils.SendMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.randomDate;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    WardMapper wardMapper;
    @Autowired
    MedicationMapper medicationMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    RunningService runningService;


    @Test
    void createDepartment() {
        Department department = new Department();
        department.setName("内科");
        department.setLocation("201");
        departmentMapper.insert(department);

        Department department1 = new Department();
        department1.setName("外科");
        department1.setLocation("202");
        departmentMapper.insert(department1);

        Department department2 = new Department();
        department2.setName("儿科");
        department2.setLocation("203");
        departmentMapper.insert(department2);

        Department department3 = new Department();
        department3.setName("妇产科");
        department3.setLocation("204");
        departmentMapper.insert(department3);

        Department department4 = new Department();
        department4.setName("急诊科");
        department4.setLocation("205");
        departmentMapper.insert(department4);

        Department department5 = new Department();
        department5.setName("骨科");
        department5.setLocation("206");
        departmentMapper.insert(department5);
    }

    @Test
    void createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName("杨开源");
        doctor.setAge(38);
        doctor.setGender(0);
        doctor.setPhone("18702860678");
        doctor.setPassword("ykycqu2004");
        doctor.setDegree("博士后");
        doctor.setTitle("教授");
        doctor.setDepartmentId(1826793027478867970L);
        doctorMapper.insert(doctor);

        Doctor doctor1 = new Doctor();
        doctor1.setName("黄瑞杰");
        doctor1.setAge(20);
        doctor1.setGender(0);
        doctor1.setPhone("18011651781");
        doctor1.setPassword("20040421a");
        doctor1.setDegree("博士");
        doctor1.setTitle("副教授");
        doctor1.setDepartmentId(1826793027415953410L);
        doctorMapper.insert(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("张瀚文");
        doctor2.setAge(34);
        doctor2.setGender(0);
        doctor2.setPhone("18180806177");
        doctor2.setPassword("Zhang02110016");
        doctor2.setDegree("硕士");
        doctor2.setTitle("主治医师");
        doctor2.setDepartmentId(1826793026329628674L);
        doctorMapper.insert(doctor2);
    }

    @Test
    public void createWard() {
        for (int roomNumber = 4; roomNumber <= 5; roomNumber++) {
            Ward ward = new Ward();
            ward.setRoomNumber(String.valueOf(roomNumber));
            ward.setDepartmentName("骨科");
            wardMapper.insert(ward);
        }

    }

    @Test
    void createMedication() {
        // 定义十种药品的名称、价格和库存
        String[] medicationNames = {"阿莫西林", "头孢", "扑热息痛", "布洛芬", "青霉素", "红霉素", "维生素C", "氯霉素", "阿司匹林", "甲硝唑"};
        int[] medicationPrices = {30, 50, 10, 20, 40, 35, 15, 25, 5, 12};
        int[] medicationStocks = {500, 300, 700, 400, 600, 200, 1000, 450, 800, 150};

        // 循环插入十种药品
        for (int i = 0; i < 10; i++) {
            Medication medication = new Medication();
            medication.setName(medicationNames[i]);
            medication.setPrice(medicationPrices[i]);
            medication.setStock(medicationStocks[i]);
            medicationMapper.insert(medication);
        }
    }

    @Test
    void createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("root");
        admin.setPassword("123456");
        adminMapper.insert(admin);
    }

    @Test
    void createFeedBack() {
        for (int i = 1; i <= 47; i++) {
            Feedback feedback = new Feedback();
            feedback.setPatientName("Patient " + i);
            feedback.setCreateTime("2024-08-27T14:30:45");  // 示例时间
            feedback.setPhone("1234567890" + i);  // 示例手机号
            feedback.setType("Type " + i);  // 示例类型
            feedback.setContent("Content " + i);  // 示例内容
            // 默认的 image 字段已在类中初始化为默认值
            feedbackMapper.insert(feedback);
        }
    }

    @Test
    public void insertDoctors() {
        List<Long> departmentIds = Arrays.asList(
                1826793026329628674L,
                1826793027415953410L,
                1826793027415953411L,
                1826793027478867970L,
                1826793027478867972L
        );

        List<String> chineseNames = Arrays.asList(
                "张三", "李四", "王五", "赵六", "孙七",
                "周八", "吴九", "郑十", "刘一", "陈二",
                "韩三", "杨四", "朱五", "秦六", "尤七",
                "许八", "何九", "吕十", "施一", "张二",
                "陆三", "谢四", "沈五", "韩六", "杨七",
                "朱八", "秦九", "尤十", "许一", "何二",
                "吕三", "施四", "张五", "陆六", "谢七",
                "沈八", "韩九", "杨十", "朱一", "秦二"
        );

        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            Doctor doctor = new Doctor();
            doctor.setName(chineseNames.get(i)); // 使用中文名字
            doctor.setGender(random.nextInt(2)); // 随机性别
            doctor.setAge(30 + random.nextInt(21)); // 年龄范围 30-50
            doctor.setPhone("1390000" + String.format("%04d", random.nextInt(10000))); // 随机生成手机号
            doctor.setPassword("e10adc3949ba59abbe56e057f20f883e"); // 简单的密码设置
            doctor.setDegree("博士"); // 学位
            doctor.setTitle("主任医师"); // 职位
            doctor.setDepartmentId(departmentIds.get(random.nextInt(departmentIds.size()))); // 随机选择一个 departmentId
            doctor.setBiography("这是 " + chineseNames.get(i) + " 的简介"); // 简介
            doctor.setFee(100 + random.nextInt(200)); // 随机费用
            doctor.setDuty(random.nextInt(2)); // 随机职责

            doctorMapper.insert(doctor); // 插入医生数据
        }
    }

    @Test
    public void testInsertRunningRecords() {
        List<Running> runningList = new ArrayList<>();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String[] sorts = {"支出", "收入"};
        String[] types = {"采购", "销售", "租赁", "维修", "广告", "工资", "税务", "办公用品"};
        String[] statuses = {"待支付", "已支付"};
        String[] operatemen = {"张伟", "王强", "李静", "赵敏", "刘洋"};
        Long[] departments = {
                1826793026329628674L,
                1826793027415953410L,
                1826793027415953411L,
                1826793027478867970L,
                1826793027478867972L
        };

        for (int i = 0; i < 40; i++) {
            Running running = new Running();
            running.setId(null); // 使用 MyBatis-Plus 自动生成 ID
            running.setDate(sdf.format(randomDate("2023-01-01", "2023-12-31")));
            running.setSort(sorts[random.nextInt(sorts.length)]);
            running.setName("Transaction " + (i + 1));
            running.setType(types[random.nextInt(types.length)]);
            int quantity = random.nextInt(100) + 1;
            int price = random.nextInt(1000) + 1;
            running.setQuantity(quantity);
            running.setPrice(price);
            running.setTotal(quantity * price);
            running.setStatus(statuses[random.nextInt(statuses.length)]);
            running.setOperateman(operatemen[random.nextInt(operatemen.length)]);
            running.setDepartment(departments[random.nextInt(departments.length)]);
            running.setPayway("在线支付"); // 示例支付方式
            running.setDescription("交易描述 " + (i + 1));

            runningList.add(running);
        }

        // 使用 MyBatis-Plus 的 saveBatch 方法批量插入
        runningService.saveBatch(runningList);
    }

    private Date randomDate(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long randomTime = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            return new Date(randomTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


