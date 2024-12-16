
# 简介
**医院管理系统，手机端已上线 [815hospital.top](815hospital.top)**

# 1.接口说明

- 接口地址：http://192.168.1.191:8080/
- 在登陆时，会将用户id放到Session中，所以后续请求接口时不用再传入当前用户的id，后端可以直接从session获取
- 数据请求统一使用data-from格式
- 例如登录请求体格式：

```JSON
{
    "username": "john_doe",
    "gender": 1,
    "password": "securePassword123",
    "phoneNum": "1234567890",
    "idNumber": "123456789012345678",
    "photo": "iVBORw0KGgoAAAANSUhEUgAAAAUA...",
    "address": "123 Main St, Anytown, USA"
}
```

- 数据返回统一使用`json`格式
- 响应体格式如下：

```JSON
{
    "code": 1,
    "msg": "Success",
    "data": {
        "key1": "value1",
        "key2": "value2"
    }
}
```

- `1`表示成功
- `0`表示后端返回失败
- `400`: 表示客户端错误。
- `500`: 表示服务器内部错误。
- 后端处理失败统一返回相应信息：

```JSON
{
    "code": 0,
    "msg": "验证码错误",
}
```

返回错误时`data`为`null`，==前端可以设计当接收到 code = 0 时弹出警告窗口==

- 除了注册用户和修改信息用json格式，其他所有post都使用form-data
- form传递使用方法

```JavaScript
const formData = new FormData();
formData.append("department", "内科");
formData.append("file", document.querySelector('input[type="file"]').files[0]);

fetch('/your-endpoint', {
    method: 'POST',
            body: formData
});
```

# 2.登录注册页面

## 2.1注册

- 请求路径：`/``sign`
- 请求方法：`post`, 此处需要用json格式传入
- 前端使用方法

```JavaScript
const data = {
department: "内科"
        };,

fetch('/your-endpoint', {
    method: 'POST',
            headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
});
```

- 请求参数

| 参数名      | 参数说明 | 备注 |
| ----------- | -------- | ---- |
| password    | 密码     | 非空 |
| phone       | 手机号   | 非空 |
| messageCode | 验证码   | 非空 |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "Success"
}
```

## 2.2获取短信验证码

- 请求路径：`/sendSms`
- 请求方法：post
- 请求参数

| 参数名   | 参数说明 | 备注 |
| -------- | -------- | ---- |
| phoneNum | 手机号   |      |

- 返回格式：

```JSON
{
    "code": 1,
    "msg": "Success"
    "data":{}
}
```

## 2.3显示验证码图片

- 请求路径：`/captcha`
- 请求方法：`get`
- 请求参数：null
- 使用方式

```JSON
<img src="/captcha" alt="CAPTCHA">
```

## 2.4登录

- 请求路径：`/login`
- 请求方法：post
- 请求参数

| 参数名   | 参数说明   | 备注 |
| -------- | ---------- | ---- |
| phone    | 手机号     | 非空 |
| password | 密码       | 非空 |
| code     | 图像验证码 | 非空 |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "Success",
    "data": {
        "role": 1
    }
}
//role = 0代表患者，role = 1代表医生
```

## 2.4 登出

- 请求路径：/logout
- 请求方法：get
- 请求参数 null
- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": null
}
```

# 3.患者端功能

## 3.1 首页

### 3.1.1 展示所有医生（预约挂号）

- 请求路径：/patient/showdoc
- 请求方法：get
- 请求参数

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| null       |              |          |

- 返回参数

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "departmentName": "外科",
            "doctorName": "韩九"
        },
        {
            "departmentName": "妇产科",
            "doctorName": "杨十"
        },
        {
            "departmentName": "骨科",
            "doctorName": "朱一"
        },
        {
            "departmentName": "儿科",
            "doctorName": "秦二"
        },
        {
            "departmentName": "内科",
            "doctorName": "豪赤"
        }
    ]
}
```

### 3.1.2 预约挂号

- 请求路径：`/patient/appoint`
- 请求方法：post
- 请求参数

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| doctorName | 医生名字     | 非空     |
| date       | 预约时间     | 非空     |
| department | 部门         | 非空     |

- 前端`date`参数请求方式（gpt生成请自行核对）

```JavaScript
// 获取当前日期
const today = new Date();
const tomorrow = new Date(today);
tomorrow.setDate(today.getDate() + 1);

// 生成时间选项
const options = [
    new Date(tomorrow.setHours(10, 0, 0, 0)).toISOString(),  // 明天的10:00
    new Date(tomorrow.setHours(11, 0, 0, 0)).toISOString(),  // 明天的11:00
    // 其他时间选项...
];

// 用户选择一个时间
const selectedTime = options[0];  // 假设用户选择了第一个选项

// 使用 axios 发送 POST 请求，参数名改为 date
axios.post('your-api-endpoint', {
    date: selectedTime
}, {
    headers: {
        'Content-Type': 'application/json',
    }
})
.then(response => {
    console.log('Success:', response.data);
})
.catch(error => {
    console.error('Error:', error);
});
```

- 返回格式

```JSON
{
    "code": 1,
    "msg": "Success"
}
```

## 3.2 个人中心

### 3.2.1 患者个人信息

- 请求路径：`/patient/``showpatient`
- 请求方法：get
- 请求参数

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| null       |              | 非空     |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "patient": {
            "id": 1826815329465638913,
            "username": "陈鸿",
            "gender": 1,
            "phone": "13060205248",
            "password": "e10adc3949ba59abbe56e057f20f883e",
            "idCard": "510524200404210199",
            "photo": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/425f6df8-8053-4638-b427-3a7bd1fd7559.jpg",
            "address": "中国科学院大学",
            "messageCode": "7250",
            "registrationId": 1827565589334978562
        },
        "inquiryTime": "2024-08-26-10:00"
    }
}

{"code":0,"msg":"NOT_LOGIN"}
```

### 3.2.2 挂号清单

- 请求路径：/patient/showreg
- 请求方法:get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 1826832884225908737,
            "doctorName": "张瀚文",
            "patientName": "陈鸿",
            "doctorId": 1826796150926708738,
            "patientId": 1826815329465638913,
            "fee": 5,
            "inquiryTime": "2024-08-23-10:00",
            "department": "内科",
            "flag": 0
        },
        {
            "id": 1827565589334978562,
            "doctorName": "黄瑞杰",
            "patientName": "陈鸿",
            "doctorId": 1826796150863794177,
            "patientId": 1826815329465638913,
            "fee": 10,
            "inquiryTime": "2024-08-26-10:00",
            "department": "外科",
            "flag": 0
        },
        {
            "id": 1828251361696395266,
            "doctorName": "黄瑞杰",
            "patientName": "陈鸿",
            "doctorId": 1826796150863794177,
            "patientId": 1826815329465638913,
            "fee": 10,
            "inquiryTime": "2024-08-26-12:00",
            "department": "外科",
            "flag": 0
        }
    ]
}
```

### 3.2.3 缴费清单

- 请求路径：/patient/showpayment
- 请求方法:get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "medicationName": "头孢",
            "amountDue": 150,
            "quantity": 3,
            "flag": 0,
            "price": 50,
            "id": "1827584856767938562"
        }
    ]
}
```

### 3.2.4 完成缴费

- 请求路径：/patient/dopayment
- 请求方法:post
- 请求参数:

```JavaScript
const paymentIdList = ['paymentId1', 'paymentId2', 'paymentId3']; 
axios.post('your-backend-endpoint-url', paymentIdList)
```

### 3.2.4 提交意见反馈

- 请求路径：/patient/feedback
- 请求方法:post
- 请求参数:

| **参数名**  | **参数说明** | **备注** |
| ----------- | ------------ | -------- |
| patientName | 姓名         |          |
| createTime  | 生成日期     |          |
| phone       | 电话         |          |
| type        | 类型         |          |
| content     | 意见文字     |          |
| image       | 图片         |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "Success",
    "data": {}
}
```

### 3.2.5 历史反馈

- 请求路径：/patient/showfeedback
- 请求方法:get
- 请求参数:

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 3,
            "patientName": "小杨开源",
            "createTime": "2024-08-28 06:19:52",
            "phone": "18702860678",
            "type": "吐槽",
            "content": "你说得对",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/a4ab0199-60a1-4273-944a-cb3c3c078f62.png",
            "patientId": 1826815329465638913
        },
        {
            "id": 2,
            "patientName": "yky hjhh",
            "createTime": "2024-08-28 06:10:04",
            "phone": "12345678910",
            "type": "投诉",
            "content": "hahahah",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e76463a5-5ff7-4122-a76a-fac488125a27.jpg",
            "patientId": 1826815329465638913
        }
    ]
}
```

## 3.3消息中心

### 3.3.1 消息展示

- 请求路径：/patient/showqst
- 请求方法: get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "askAnswer2": {
            "id": 1,
            "patientId": 1826815329465638913,
            "doctorId": null,
            "question": "我要治病尿酸21:46",
            "answer": null
        },
        "askAnswer1": {
            "id": 2,
            "patientId": 1826815329465638913,
            "doctorId": null,
            "question": "我治好了尿酸",
            "answer": null
        }
    }
}
//"askAnswer1"是最新的消息
```

### 3.3.2 提问功能

- 请求路径：/patient/ask
- 请求方法:Post
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| question   | 病人写的问题 | 非空     |

```JSON
{
    "code": 1,
    "msg": "Success",
    "data": {   
    }
}
```

## 3.4 设置

### 3.4.3 显示已经修改的信息

- 请求路径：/patient/showready
- 请求方法: get
- 请求参数:null

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "address": "中国科学院大学",
        "gender": "1",
        "phone": "13060205248",
        "idcard": "510524200404210199",
        "name": "陈鸿"
    }
}
```

### 3.4.3 修改信息

- 请求路径：/patient/setinfo
- 请求方法: post
- 请求参数:此处需要用form-data格式

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| name       | 姓名         |          |
| idCard     | 身份证号     |          |
| gender     | 性别         |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "Success"
}
```

### 3.4.4 上传头像

- 请求路径：/patient/setimage
- 请求方法: post
- 请求参数:此处需要用form-data格式

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| image      | 性别         |          |

```JSON
{
    "code": 1,
    "msg": "Success"
}
```

## 3.5住院服务

### 3.5.1 显示默认病房信息

- 请求路径：/patient/setimage
- 请求方法: post
- 请求参数:

| 参数名 | 参数说明 | 备注 |
| ------ | -------- | ---- |
| image  |          |      |

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "1": 1,
        "2": 0,
        "3": 0,
        "4": 1,
        "5": 0,
        "rate": "72.0%",
        "history": 1028,
        "remaining": 18
    }
}
//0 = 空；1 = 满
```

### 3.5.2 显示特定科室信息

- 请求路径：/patient/showspecward
- 请求方法: post
- 请求参数:

| 参数名         | 参数说明 | 备注 |
| -------------- | -------- | ---- |
| departmentName | 科室名   |      |

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "1": 0,
        "2": 0,
        "3": 0,
        "4": 1,
        "5":0
    }
}
//0 = 空；1 = 满
```

### 3.5.3 选择空病房

- 请求路径：/patient/selectward
- 请求方法: post
- 请求参数: （只需要传用户选择的病房号字符串类型，科室名会在上一步通过Session保存）

| 参数名     | 参数说明 |      | 备注 |
| ---------- | -------- | ---- | ---- |
| roomNumber | 病房号   |      | 无   |

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "rate": 68.0,
        "history": 1029,
        "remaining": 17
    }
}
```

# 4.医生端功能

## 4.0 设置个人信息

- 请求路径：/doctor/setinfo
- 请求方法:post
- 请求参数:

| title        | 职称   |
| ------------ | ------ |
| biography    | 简介   |
| departmentId | 科室id |
| image        | 头像   |

## 4.1消息中心

### 4.1.0 展示个人信息

- 请求路径：/doctor/showme
- 请求方法:get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "todo": 2,
        "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
        "phone": "18011651781",
        "name": "黄瑞杰",
        "id": 1826796150863794177
    }
}
```

### 4.1.1 消息展示

- 请求路径：/doctor/showinhome
- 请求方法:get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回数据：

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "patientName": "boss",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
            "content": "快点上班"
        },
        {
            "patientName": "boss",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
            "content": "这是19:07发送的一条消息"
        },
        {
            "patientName": "boss",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
            "content": "请马上到急症室"
        },
        {
            "patientName": "陈鸿",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/3e1cd4ea-7770-4325-815c-a3419e1ee39c.png",
            "question": "9：29陈鸿你好",
            "askanswerId": "4"
        },
        {
            "patientName": "陈鸿",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/3e1cd4ea-7770-4325-815c-a3419e1ee39c.png",
            "question": "这是陈鸿8月27号的询问，我应该怎么治我的尿酸",
            "askanswerId": "3"
        },
        {
            "patientName": "陈鸿",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/3e1cd4ea-7770-4325-815c-a3419e1ee39c.png",
            "question": "我治好了尿酸",
            "askanswerId": "2"
        },
        {
            "patientName": "陈鸿",
            "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/3e1cd4ea-7770-4325-815c-a3419e1ee39c.png",
            "question": "我要治病尿酸21:46",
            "askanswerId": "1"
        }
    ]
}
```

### 4.1.2 回答问题

- 请求路径：/doctor/answer
- 请求方法:Post
- 请求参数:

| **参数名**  | **参数说明** | **备注** |
| ----------- | ------------ | -------- |
| answer      | 医生的回答   | 非空     |
| askanswerId | 问题的序号   | 非空     |

### 4.1.3 管理员的通知

- 请求路径：/doctor/shownoti
- 请求方法: get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| null       |              |          |

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 1827562217395535874,
            "doctorId": 1826796150863794177,
            "content": "请马上到急症室"
        }
    ]
}
```

## 4.2 我的患者

### 4.2.1 显示所有患者

- 请求路径：/doctor/showpatients
- 请求方法: get
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| 无         |              |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "phone": "13060205248",
            "inquiryTime": "2024-08-26-10:00",
            "name": "陈鸿",
            "id": "1827565589334978562"
        },
        {
            "phone": "13060205248",
            "inquiryTime": "2024-08-26-12:00",
            "name": "陈鸿",
            "id": "1828251361696395266"
        }
    ]
}
```

### 4.2.2 诊断

#### 4.2.1 显示所有药品

- 请求路径：/doctor/showmedi
- 请求方法: get
- 请求参数: null
- 返回格式

```JavaScript
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 1827552448786055169,
            "name": "阿莫西林",
            "price": 30,
            "stock": 500
        },
        {
            "id": 1827552449964654593,
            "name": "头孢",
            "price": 50,
            "stock": 300
        },
        {
            "id": 1827552450027569153,
            "name": "扑热息痛",
            "price": 10,
            "stock": 700
        }
    ]
}
```

#### 4.2.2 创建诊断单

- 请求路径：/doctor/payment
- 请求方法: post
- 请求参数:

| **参数名**   | **参数说明** | **备注**                                                     |
| ------------ | ------------ | ------------------------------------------------------------ |
| diagnosis    | 诊断意见     |                                                              |
| medicationId | 药品id       |                                                              |
| quantity     | 药品数量     |                                                              |
| patientId    | 病人id       | 请在上一步显示所有患者中，保存当前浏览的病人id，再从此处传参给后端 |

- 返回格式

```JavaScript
{
    "code": 1,
    "msg": "Success"
}
```

#### 4.4.3 展示所有诊断

# 5.后台管理

## 5.1 管理员给医生发送消息

- 请求路径：/admin/sendmsg
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| doctorName | 医生名称     |          |
| content    | 通知内容     |          |

- 返回格式

```JavaScript
{
    "code": 1,
    "msg": "Success"
}
```

## 5.2 分页展示患者

- 请求路径：/admin/showpatient
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明**   | **备注** |
| ---------- | -------------- | -------- |
| page       | 当前页面       |          |
| pageSize   | 每页显示的数量 |          |

- 返回格式

```JavaScript
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 1826815329465638913,
            "username": "陈鸿",
            "gender": 1,
            "phone": "13060205248",
            "password": "e10adc3949ba59abbe56e057f20f883e",
            "idCard": "510524200404210199",
            "photo": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
            "address": "中国科学院大学",
            "messageCode": "7250",
            "registrationId": 1827565589334978562
        },
        {
            "id": 1826815329465638913,
            "username": "杨开源",
            "gender": 1,
            "phone": "13060205248",
            "password": "e10adc3949ba59abbe56e057f20f883e",
            "idCard": "510524200404210199",
            "photo": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png",
            "address": "中国科学院大学",
            "messageCode": "7250",
            "registrationId": 1827565589334978562
        }
    ]
}
```

## 5.3 分页展示医生

- 请求路径：/admin/showdoctor
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明**   | **备注** |
| ---------- | -------------- | -------- |
| page       | 当前页面       |          |
| pageSize   | 每页显示的数量 |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 3,
        "records": [
            {
                "id": 1826796149613891586,
                "name": "杨开源",
                "gender": 0,
                "age": 38,
                "phone": "18702860678",
                "password": "1b67088ad36adbff22cdc17efce84bbd",
                "degree": "博士后",
                "title": "教授",
                "departmentId": 1826793027478867970,
                "biography": "杨开源，教授，博士后学历，妇产科专家，擅长高危妊娠及复杂产科手术。拥有丰富的临床经验，尤其在妇科肿瘤及产前诊断方面有深厚造诣。多次在国际期刊发表研究成果，并积极参与妇产科新技术的推广与应用。",
                "fee": 15,
                "duty": 1
            },
            {
                "id": 1826796150863794177,
                "name": "黄瑞杰",
                "gender": 0,
                "age": 20,
                "phone": "18011651781",
                "password": "425672e929b2cd6c9f6b411493edfb07",
                "degree": "博士",
                "title": "副教授",
                "departmentId": 1826793027415953410,
                "biography": "黄瑞杰，副教授，博士学历，外科领域新锐，年仅20岁即成为副教授，展现出惊人的学术潜力。专注于微创外科手术及器官移植研究，发表多篇高影响力论文，是外科创新领域的先锋，深受同行认可。",
                "fee": 10,
                "duty": 1
            }
        ]
    }
}
```

## 5.4 调整医生在岗情况

- 请求路径：/admin/duty
- 请求方法: post
- 请求参数:

| doctorId | 医生id |
| -------- | ------ |
|          |        |

```JSON
{
    "code": 1,
    "msg": "success"
}
```

## 5.5 编辑医生信息

- 请求路径：/admin/editdoc
- 请求方法: post
- 请求参数:

| **参数名**     | **参数说明** | **备注** |
| -------------- | ------------ | -------- |
| doctorName     | 医生姓名     |          |
| doctorId       | 医生id       |          |
| phone          | 电话         |          |
| age            | 年龄         |          |
| gender         | 性别         |          |
| departmentName | 部门         |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success"
}
```

## 5.6 删除医生

- 请求路径：/admin/deletedoc
- 请求方法: post
- 请求参数：

| doctorId | 医生id |
| -------- | ------ |
|          |        |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success"
}
```

## 5.7 显示所有医生

- 请求路径：/admin/showalldoctor
- 请求方法: get
- 请求参数:null
- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        "杨开源",
        "黄瑞杰",
        "张瀚文"
    ]
}
```

## 5.8 分页显示反馈

- 请求路径：/admin/showfeedback
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明**   | **备注** |
| ---------- | -------------- | -------- |
| page       | 当前页数       |          |
| pageSize   | 每页显示的条数 |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 47,
        "records": [
            {
                "id": 79,
                "patientName": "Patient 31",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789031",
                "type": "Type 31",
                "content": "Content 31",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 80,
                "patientName": "Patient 32",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789032",
                "type": "Type 32",
                "content": "Content 32",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 81,
                "patientName": "Patient 33",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789033",
                "type": "Type 33",
                "content": "Content 33",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 82,
                "patientName": "Patient 34",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789034",
                "type": "Type 34",
                "content": "Content 34",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 83,
                "patientName": "Patient 35",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789035",
                "type": "Type 35",
                "content": "Content 35",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 84,
                "patientName": "Patient 36",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789036",
                "type": "Type 36",
                "content": "Content 36",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 85,
                "patientName": "Patient 37",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789037",
                "type": "Type 37",
                "content": "Content 37",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 86,
                "patientName": "Patient 38",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789038",
                "type": "Type 38",
                "content": "Content 38",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 87,
                "patientName": "Patient 39",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789039",
                "type": "Type 39",
                "content": "Content 39",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            },
            {
                "id": 88,
                "patientName": "Patient 40",
                "createTime": "2024-08-27T14:30:45",
                "phone": "123456789040",
                "type": "Type 40",
                "content": "Content 40",
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ee52ced8-aebd-4cc9-8812-860b7f559be8.png"
            }
        ]
    }
}
```

## 5.9 模糊查询医生

- 请求路径：/admin/searchdoc
- 请求方法: post
- 请求参数:

| doctorName | 医生名字 |
| ---------- | -------- |
|            |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 8,
        "doctors": [
            {
                "gender": 1,
                "phone": "13900006999",
                "departmentId": 1826793027415953411,
                "name": "陈二",
                "duty": 1,
                "id": "1828420205530349569",
                "age": 49
            },
            {
                "gender": 1,
                "phone": "13900008556",
                "departmentId": 1826793027415953411,
                "name": "张二",
                "duty": 0,
                "id": "1828420208378281985",
                "age": 35
            },
            {
                "gender": 1,
                "phone": "13900000119",
                "departmentId": 1826793027478867970,
                "name": "何二",
                "duty": 1,
                "id": "1828420211393986561",
                "age": 31
            },
            {
                "gender": 1,
                "phone": "13900001919",
                "departmentId": 1826793026329628674,
                "name": "秦二",
                "duty": 1,
                "id": "1828420214262890497",
                "age": 35
            },
            {
                "gender": 1,
                "phone": "13900005110",
                "departmentId": 1826793026329628674,
                "name": "陈二",
                "duty": 1,
                "id": "1828420650403373057",
                "age": 48
            },
            {
                "gender": 1,
                "phone": "13900000935",
                "departmentId": 1826793027415953410,
                "name": "张二",
                "duty": 0,
                "id": "1828420653255499777",
                "age": 45
            },
            {
                "gender": 0,
                "phone": "13900005840",
                "departmentId": 1826793027478867972,
                "name": "何二",
                "duty": 1,
                "id": "1828420656074072065",
                "age": 44
            },
            {
                "gender": 0,
                "phone": "13900003002",
                "departmentId": 1826793027415953411,
                "name": "秦二",
                "duty": 1,
                "id": "1828420658838118402",
                "age": 43
            }
        ]
    }
}
```

## 5.10 分页显示药品

- 请求路径：/admin/showmedi
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明**   | **备注** |
| ---------- | -------------- | -------- |
| page       | 当前页数       |          |
| pageSize   | 每页显示的条数 |          |

- 返回参数

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 10,
        "records": [
            {
                "id": 1827552450027569156,
                "name": "红霉素",
                "price": 35,
                "stock": 200,
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/a166ad01-7409-4188-8b8c-1478771bd321.png"
            },
            {
                "id": 1827552450090483713,
                "name": "维生素C",
                "price": 15,
                "stock": 1000,
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/ad74502e-c822-4cb7-a684-fc2c0edaf46d.png"
            },
            {
                "id": 1827552450090483714,
                "name": "氯霉素",
                "price": 25,
                "stock": 450,
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/2b9b4649-c584-4293-b2a6-8b35bb4f4bdc.png"
            },
            {
                "id": 1827552450090483715,
                "name": "阿司匹林",
                "price": 5,
                "stock": 800,
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/95b95d30-28f0-4f19-84fb-be5b22461a27.png"
            },
            {
                "id": 1827552450090483716,
                "name": "人工牛黄甲硝唑",
                "price": 12,
                "stock": 150,
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/53ea0e49-3fe1-45b3-924c-094aaca867c8.png"
            }
        ]
    }
}
```

## 5.11 模糊查询药品

##  

- 请求路径：/admin/searchmedi
- 请求方法: post
- 请求参数:

| medicationName | 药品名字 |
| -------------- | -------- |
|                |          |

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 2,
        "medications": [
            {
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/d09fce33-e04e-4425-81c7-d152e8d779d6.png",
                "price": 30,
                "name": "阿莫西林",
                "id": "1827552448786055169",
                "stock": 500
            },
            {
                "image": "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/95b95d30-28f0-4f19-84fb-be5b22461a27.png",
                "price": 5,
                "name": "阿司匹林",
                "id": "1827552450090483715",
                "stock": 800
            }
        ]
    }
}
```

## 5.12 分页显示流水

- 请求路径：/admin/showrun
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明**   | **备注** |
| ---------- | -------------- | -------- |
| page       | 当前页数       |          |
| pageSize   | 每页显示的条数 |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 40,
        "records": [
            {
                "id": 1828529440092184607,
                "date": "2023-11-14",
                "sort": "收入",
                "name": "Transaction 31",
                "type": "采购",
                "quantity": 91,
                "price": 170,
                "total": 15470,
                "status": "已支付",
                "payway": "在线支付",
                "operateman": "张伟",
                "description": "交易描述 31",
                "department": 1826793026329628674
            },
            {
                "id": 1828529440092184608,
                "date": "2023-10-17",
                "sort": "支出",
                "name": "Transaction 32",
                "type": "工资",
                "quantity": 21,
                "price": 890,
                "total": 18690,
                "status": "已支付",
                "payway": "在线支付",
                "operateman": "刘洋",
                "description": "交易描述 32",
                "department": 1826793027478867970
            },
            {
                "id": 1828529440092184609,
                "date": "2023-12-07",
                "sort": "支出",
                "name": "Transaction 33",
                "type": "工资",
                "quantity": 4,
                "price": 244,
                "total": 976,
                "status": "待支付",
                "payway": "在线支付",
                "operateman": "王强",
                "description": "交易描述 33",
                "department": 1826793027478867970
            },
            {
                "id": 1828529440092184610,
                "date": "2023-09-22",
                "sort": "收入",
                "name": "Transaction 34",
                "type": "维修",
                "quantity": 59,
                "price": 934,
                "total": 55106,
                "status": "已支付",
                "payway": "在线支付",
                "operateman": "赵敏",
                "description": "交易描述 34",
                "department": 1826793027478867970
            },
            {
                "id": 1828529440159293442,
                "date": "2023-10-11",
                "sort": "支出",
                "name": "Transaction 35",
                "type": "租赁",
                "quantity": 42,
                "price": 860,
                "total": 36120,
                "status": "已支付",
                "payway": "在线支付",
                "operateman": "赵敏",
                "description": "交易描述 35",
                "department": 1826793027478867972
            },
            {
                "id": 1828529440159293443,
                "date": "2023-02-18",
                "sort": "收入",
                "name": "Transaction 36",
                "type": "广告",
                "quantity": 94,
                "price": 899,
                "total": 84506,
                "status": "待支付",
                "payway": "在线支付",
                "operateman": "张伟",
                "description": "交易描述 36",
                "department": 1826793026329628674
            },
            {
                "id": 1828529440159293444,
                "date": "2023-06-07",
                "sort": "收入",
                "name": "Transaction 37",
                "type": "租赁",
                "quantity": 77,
                "price": 74,
                "total": 5698,
                "status": "待支付",
                "payway": "在线支付",
                "operateman": "张伟",
                "description": "交易描述 37",
                "department": 1826793027478867972
            },
            {
                "id": 1828529440159293445,
                "date": "2023-09-26",
                "sort": "收入",
                "name": "Transaction 38",
                "type": "销售",
                "quantity": 90,
                "price": 370,
                "total": 33300,
                "status": "待支付",
                "payway": "在线支付",
                "operateman": "赵敏",
                "description": "交易描述 38",
                "department": 1826793026329628674
            },
            {
                "id": 1828529440159293446,
                "date": "2023-09-11",
                "sort": "支出",
                "name": "Transaction 39",
                "type": "广告",
                "quantity": 78,
                "price": 137,
                "total": 10686,
                "status": "待支付",
                "payway": "在线支付",
                "operateman": "张伟",
                "description": "交易描述 39",
                "department": 1826793026329628674
            },
            {
                "id": 1828529440159293447,
                "date": "2023-10-02",
                "sort": "支出",
                "name": "Transaction 40",
                "type": "税务",
                "quantity": 5,
                "price": 840,
                "total": 4200,
                "status": "已支付",
                "payway": "在线支付",
                "operateman": "李静",
                "description": "交易描述 40",
                "department": 1826793027415953411
            }
        ]
    }
}
```

## 5.13 模糊查询流水

- 请求路径：/admin/showrun
- 请求方法: post
- 请求参数:

| **参数名** | **参数说明** | **备注** |
| ---------- | ------------ | -------- |
| runName    | 流水项目名称 |          |
| date       | 选择的日期   |          |

- 返回格式

```JSON
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 1828529440092184580,
            "date": "2023-03-10",
            "sort": "收入",
            "name": "Transaction 4",
            "type": "工资",
            "quantity": 82,
            "price": 945,
            "total": 77490,
            "status": "已支付",
            "payway": "在线支付",
            "operateman": "赵敏",
            "description": "交易描述 4",
            "department": 1826793027478867970
        },
        {
            "id": 1828529440092184590,
            "date": "2023-02-25",
            "sort": "支出",
            "name": "Transaction 14",
            "type": "广告",
            "quantity": 27,
            "price": 354,
            "total": 9558,
            "status": "已支付",
            "payway": "在线支付",
            "operateman": "张伟",
            "description": "交易描述 14",
            "department": 1826793027478867972
        },
        {
            "id": 1828529440092184600,
            "date": "2023-11-24",
            "sort": "支出",
            "name": "Transaction 24",
            "type": "租赁",
            "quantity": 93,
            "price": 268,
            "total": 24924,
            "status": "已支付",
            "payway": "在线支付",
            "operateman": "张伟",
            "description": "交易描述 24",
            "department": 1826793027478867970
        },
        {
            "id": 1828529440092184610,
            "date": "2023-09-22",
            "sort": "收入",
            "name": "Transaction 34",
            "type": "维修",
            "quantity": 59,
            "price": 934,
            "total": 55106,
            "status": "已支付",
            "payway": "在线支付",
            "operateman": "赵敏",
            "description": "交易描述 34",
            "department": 1826793027478867970
        },
        {
            "id": 1828529440159293447,
            "date": "2023-10-02",
            "sort": "支出",
            "name": "Transaction 40",
            "type": "税务",
            "quantity": 5,
            "price": 840,
            "total": 4200,
            "status": "已支付",
            "payway": "在线支付",
            "operateman": "李静",
            "description": "交易描述 40",
            "department": 1826793027415953411
        }
    ]
}
```
