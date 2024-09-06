<template>
	<div class="page flex-col">
			<div class="nav-bar_1 flex-row">
				<img class="icon_1" @click="goBack" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">个人信息修改</span>
			</div>

		<div class="box_3 flex-col justify-end">
			<span class="text_2">基本信息</span>
			<div class="input_1 flex-col justify-end">
				<div class="text-wrapper_1 flex-row">
					<span class="text_3">*</span>
					<span class="text_4">真实姓名</span>
					<input v-model="realName" :placeholder="realName ? '' : '请填写您的姓名'" class="text_5" />
				</div>
				<div class="box_4 flex-col"></div>
			</div>
			<div class="input_2 flex-col justify-end">
				<div class="text-wrapper_2 flex-row">
					<span class="text_6">*</span>
					<span class="text_7">身份证号</span>
					<input v-model="idNumber" :placeholder="idNumber ? '' : '请填写您的身份证号'" class="text_8" />
				</div>
				<div class="group_1 flex-col"></div>
			</div>
			<div class="box_5 flex-row">
				<span class="text_9">*</span>
				<span class="text_10">性别</span>
				<span @click="showGenderPicker = true" class="text_11">{{ gender || '请选择您的性别' }}</span>
				<img class="icon_2" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png" />
				<van-popup v-model="showGenderPicker" position="bottom">
					<van-picker :columns="genderOptions" show-toolbar title="选择性别" @confirm="onConfirmGender"
						@cancel="showGenderPicker = false" />
				</van-popup>
			</div>
			<div class="box_6 flex-col"></div>
			<div class="input_3 flex-col justify-end">
				<div class="text-wrapper_3 flex-row">
					<span class="text_15">*</span>
					<span class="text_16">联系电话</span>
					<input v-model="phoneNumber" readonly class="text_17" />
				</div>
				<div class="box_9 flex-col"></div>
			</div>
			<div class="text-wrapper_4 flex-row justify-between">
				<span class="text_18">现居地</span>
				<input v-model="currentResidence" :placeholder="currentResidence ? '' : '请填写现居地'" class="text_19" />
			</div>
			<div class="box_10 flex-col"></div>
		</div>
		<div class="box_11 flex-col">
			<div class="text-wrapper_5 flex-row justify-between">
				<span class="text_20">健康标签</span>
				<span class="text_21">请选择标签内容</span>
			</div>
			<div class="box_12 flex-col"></div>
			<div class="grid_1 flex-row">
				<div v-for="(tag, index) in healthTags" :key="index" @click="toggleTag(index)"
					:class="[tag.selected ? 'tag-selected' : 'tag-unselected', 'tag_1']">
					<span
						:class="[tag.selected ? 'text-selected' : 'text-unselected', 'text_22']">{{ tag.label }}</span>
				</div>
			</div>
		</div>
		<div class="box_13 flex-col">
			<button class="button_1 flex-col" @click="saveAndGoBack"><span class="text_28">确认</span></button>
			<div class="block_1 flex-col"></div>
		</div>
		<div class="box_14 flex-col"></div>
	</div>
</template>

<script>
import axios from 'axios';
import { Picker, Popup } from 'vant';

export default {
	components: {
		'van-picker': Picker,
		'van-popup': Popup
	},
	data() {
		return {
			realName: '',
			idNumber: '',
			gender: '',
			phoneNumber: '',
			currentResidence: '',
			showGenderPicker: false,
			genderOptions: ['男', '女'],
			healthTags: [
				{ label: '高血压', selected: false },
				{ label: '冠心病', selected: false },
				{ label: '高血脂', selected: false },
				{ label: '糖尿病', selected: false },
				{ label: '高血糖', selected: false },
				{ label: '低血糖', selected: false }
			]
		};
	},
	methods: {
		goBack() {
			this.$router.go(-1);
		},
		async saveAndGoBack() {
			try {
				await this.saveInformation();
				this.clearForm();
				this.$router.go(-1);
			} catch (error) {
				console.error('保存信息时出错:', error);
			}
		},
		async saveInformation() {
			const genderValue = this.gender === '男' ? '1' : '0';
			const formData = new FormData();
			formData.append('name', this.realName);
			formData.append('idCard', this.idNumber);
			formData.append('gender', genderValue);

			const response = await axios.post('/patient/setinfo', formData, {
				headers: {
					'Content-Type': 'multipart/form-data',
				},
			});

			if (response.data.code === 1) {
				this.clearForm();
				this.$router.go(-1);
			} else {
				console.error('保存信息失败:', response.data.msg);
			}
		},
		async fetchPatientInfo() {
			try {
				const response = await axios.get('/patient/showready');
				if (response.data.code === 1) {
					const patientData = response.data.data;
					this.realName = patientData.name;
					this.idNumber = patientData.idcard;
					this.gender = patientData.gender === '1' ? '男' : '女';
					this.phoneNumber = patientData.phone;
					this.currentResidence = patientData.address;
				}
			} catch (error) {
				console.error('获取个人信息失败:', error);
			}
		},
		clearForm() {
			this.realName = '';
			this.idNumber = '';
			this.gender = '';
			this.currentResidence = '';
			this.healthTags.forEach(tag => tag.selected = false);
		},
		onConfirmGender(value) {
			this.gender = value;
			this.showGenderPicker = false;
		},
		toggleTag(index) {
			this.healthTags[index].selected = !this.healthTags[index].selected;
		}
	},
	mounted() {
		this.fetchPatientInfo();
	}
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
