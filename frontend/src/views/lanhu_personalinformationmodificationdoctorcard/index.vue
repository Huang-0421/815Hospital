<template>
	<div class="page flex-col">
		<div class="box_1 flex-row">
			<img class="label_1"  @click="goBack" referrerpolicy="no-referrer"
				src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
			<span class="text_1">医生名片</span>
			<span class="text_2" @click="goToManagement">&nbsp;编辑</span>
		</div>
		<div class="box_2 flex-row">
			<img class="image_1" :src="doctorInfo.avatar" referrerpolicy="no-referrer" />
			<div class="box_3 flex-col">
				<div class="box_4 flex-row justify-between">
					<span class="text_3">{{ doctorInfo.name }}</span>
					<div class="text-wrapper_1"><span class="text_4">{{ doctorInfo.title }}</span></div>
					<!-- 动态绑定职称 -->
				</div>
				<div class="text-wrapper_2 flex-row justify-between">
					<span class="text_5">职工编号:</span>
					<span class="text_6">{{ doctorInfo.employeeId }}</span>
				</div>
				<div class="text-wrapper_3 flex-row justify-between">
					<span class="text_7">联系电话:</span>
					<span class="text_8">{{ doctorInfo.phone }}</span>
				</div>
				<div class="text-wrapper_4 flex-row">
					<span class="text_9">毕业院校：</span>
					<span class="text_10">{{ doctorInfo.school }}</span>
				</div>
				<div class="text-wrapper_5 flex-row">
					<span class="text_11">看诊科室：</span>
					<span class="text_12">{{ doctorInfo.department }}</span>
				</div>
			</div>
			<img class="label_2" referrerpolicy="no-referrer"
				src="./assets/img/MasterDDSSlicePNG4c2c095261e277d0ad7ee77528acb901.png" />
			<img class="label_3" referrerpolicy="no-referrer"
				src="./assets/img/MasterDDSSlicePNG333c879e04aa1a6925f2f2e5bd052f8b.png" />
		</div>
		<span class="text_13">医生简介</span>
		<div class="text-wrapper_6 flex-col">
			<span class="paragraph_1">{{ doctorInfo.bio }}</span>
		</div>
		<span class="text_14">擅长</span>
		<div class="text-wrapper_7 flex-col">
			<span class="text_15">{{ doctorInfo.specialty }}</span>
		</div>
	</div>
</template>


<script>
	export default {
		data() {
			return {
				doctorInfo: {
					name: "阳兰广",
					employeeId: "2512541",
					phone: "13800138000",
					school: "重庆大学",
					department: "内分泌科6室",
					bio: "1、北京大学第一医院内分泌科专家...",
					specialty: "甲状腺疾病及内分泌疾病...",
					avatar: "/path/to/avatar.jpg", // 头像图片的路径或 URL
					title: "主任医师" // 职称
				},
			};
		},
		methods: {
		    /*fetchDoctorInfo() {
		      axios.get('/api/doctor-info').then(response => {
		        this.doctorInfo = response.data;
		      }).catch(error => {
		        console.error("Error fetching doctor info:", error);
		      });
		    },*/
		    goBack() {
		      this.$router.push('/lanhu_personalcenter'); // 跳转到lanhu_personalcenter/index.vue
		    },
		    goToManagement() {
		      this.$router.push('/lanhu_personalinformationmodificationpatientend'); // 跳转到lanhu_personalinformationmodificationpatientend/index.vue
		    }
		  },
		  /*created() {
		    this.fetchDoctorInfo(); // 组件创建时获取数据
		  }*/
	};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />