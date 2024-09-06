<template>
	<div class="page flex-col">
		<div class="group_1 flex-col">
			<div class="nav-bar_1 flex-row">
				<img class="icon_1" referrerpolicy="no-referrer" 
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" 
					@click="goBack"/>
				<span class="text_1">意见反馈</span>
			</div>
			<div class="block_1 flex-col"></div>
		</div>
		<div class="group_2 flex-col">
			<div class="box_1 flex-col justify-end">
				<div class="block_2 flex-row justify-between" @click="navigateToFeedbackRecord">
					<div class="image-text_1 flex-row justify-between" >
						<img class="icon_2" referrerpolicy="no-referrer"
							src="./assets/img/MasterDDSSlicePNGb634d824cd898da7076c477fbf2645c1.png" />
						<span class="text-group_1">反馈记录</span>
					</div>
					<img class="icon_3" referrerpolicy="no-referrer"
						src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png" />
				</div>
				<div class="block_3 flex-col"></div>
			</div>
			<div class="box_2 flex-col justify-end">
				<div class="text-wrapper_1 flex-row">
					<span class="text_2">*</span>
					<span class="text_3">姓名</span>
					<input type="text" class="input-field" placeholder="请填写姓名" v-model="name" />
				</div>
				<div class="block_4 flex-col"></div>
				<div class="text-wrapper_2 flex-row">
					<span class="text_5">*</span>
					<span class="text_6">联系电话</span>
					<input type="text" class="input-field" placeholder="请填写联系电话" v-model="phone" />
				</div>
				<div class="block_5 flex-col"></div>
				<div class="text-wrapper_3 flex-row" @click="showPicker = true">
					<span class="text_8">*</span>
					<span class="text_9">问题类型</span>
					<span class="text_10">{{ selectedProblemType || '请选择问题类型' }}</span>
				</div>
				<van-popup v-model="showPicker" position="bottom">
					<van-picker :columns="problemTypes" show-toolbar title="问题类型" @confirm="onConfirmProblemType"
						@cancel="showPicker = false" />
				</van-popup>
				<div class="block_6 flex-col"></div>
			</div>
		</div>
		<div class="group_3 flex-col">
			<span class="text_11">问题描述</span>
			<div class="text-wrapper_4 flex-col">
				<textarea class="text-area" v-model="problemDescription" maxlength="200"
					placeholder="请输入问题描述"></textarea>
				<div class="char-counter">{{ problemDescription.length }}/200</div>
			</div>
		</div>
		<div class="group_4 flex-col">
			<span class="text_13">添加照片</span>
			<div class="text-wrapper_5 flex-col">
				<van-uploader v-model="fileList" multiple max-count="4" max-size="2048000" :after-read="onAfterRead" />
			</div>
			</div>
			<div class="box_3 flex-col">
				<div class="group_5 flex-col"></div>
				<button class="button_1 flex-col" @click="onClick_1"><span class="text_15">提交</span></button>
			</div>
			
	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Picker,
		Popup,
		Uploader
	} from 'vant';

	export default {
		components: {
			[Picker.name]: Picker,
			[Popup.name]: Popup,
			[Uploader.name]: Uploader,
		},
		data() {
			return {
				name: '',
				phone: '',
				showPicker: false,
				selectedProblemType: '',
				problemTypes: ['投诉', '感谢', '吐槽'], // 示例数据
				problemDescription: '',
				fileList: [], // 用于存储上传的文件
			};
		},
		methods: {
			onClick_1() {
				this.saveFeedbackData();
			},
			navigateToFeedbackRecord() {
				this.$router.push({
					path: '/lanhu_feedbackfeedbackrecord'
				});
			},
			onConfirmProblemType(value) {
				this.selectedProblemType = value;
				this.showPicker = false;
			},
			onAfterRead(file) {
				this.fileList.push(file.file); // 确保fileList中存的是File对象
			},
			saveFeedbackData() {
				// 获取当前时间
				const now = new Date();
				const createTime = now.getFullYear() + '-' + 
					(String(now.getMonth() + 1).padStart(2, '0')) + '-' +
					(String(now.getDate()).padStart(2, '0')) + ' ' +
					(String(now.getHours()).padStart(2, '0')) + ':' +
					(String(now.getMinutes()).padStart(2, '0')) + ':' +
					(String(now.getSeconds()).padStart(2, '0'));

				// 创建FormData对象
				const formData = new FormData();
				formData.append('patientName', this.name);
				formData.append('createTime', createTime);
				formData.append('phone', this.phone);
				formData.append('type', this.selectedProblemType);
				formData.append('content', this.problemDescription);

				// 处理多张图片上传
				this.fileList.forEach((file) => {
					formData.append('image', file);
				});

				// 发起POST请求
				axios.post('/patient/feedback', formData, {
					headers: {
						'Content-Type': 'multipart/form-data'
					}
				}).then(response => {
					if (response.data.code === 1) {
						this.$router.push({
							path: 'lanhu_feedbacksubmittedsuccessfully'
						});
					} else {
						this.$message.error('提交失败');
					}
				}).catch(error => {
					console.error('提交出错:', error);
					this.$message.error('提交出错');
				});
			},
			goBack() {
				this.$router.go(-1);
			}
		},
	};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
