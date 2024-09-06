<template>
	<div class="page flex-col">
		<div class="group_1 flex-col">
			<div class="nav-bar_1 flex-row">
				<img class="icon_1" @click="goBack" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">系统消息</span>
			</div>
			<div class="box_1 flex-col"></div>
		</div>
		<div class="group_2 flex-col" v-if="messages.length > 0">
			<div v-for="(message, index) in messages" :key="index">
				<!-- 动态显示消息时间 -->
				<div class="text-wrapper_1 flex-row"><span class="text_2">{{ message.sendTime }}</span></div>
				<div class="block_1 flex-row justify-between">
					<img class="icon_2" referrerpolicy="no-referrer"
						src="./assets/img/MasterDDSSlicePNGa7c2d0c3390b2c0c04ad00f28bd49643.png" />
					<div class="group_3 flex-col">
						<div class="text-wrapper_2">
							<!-- 动态显示消息内容 -->
							<span class="text_3">{{ message.messageContent }}</span>
							<span class="text_4">详情请点击</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				messages: [], // 初始化为空数组，后续从后端获取数据
			};
		},
		methods: {
			goBack() {
				// 返回上一页，不保存信息
				this.$router.push({
					path: 'lanhu_messagecenter'
				});
			},
			fetchMessages() {
				// 模拟从后端获取数据
				const dataFromBackend = [{
						sendTime: "下午02:30",
						messageContent: "新版本来啦~八一五医院便民小程序迎来2.2.1版本更新。新版本优化了界面设计，大大提升了居民使用体验。",
					},
					// 你可以继续添加更多模拟数据
				];

				// 更新数据
				this.messages = dataFromBackend;

				// 如果没有消息，跳转到指定的空界面
				if (this.messages.length === 0) {
					this.redirectToEmptyPage();
				}
			},
			redirectToEmptyPage() {
				this.$router.push({
					path: 'lanhu_messagecentersystemmessagesempty'
				});
			},
		},
		mounted() {
			// 当组件挂载时，调用获取数据的方法
			this.fetchMessages();
		},
	};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />