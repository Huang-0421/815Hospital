<template>
	<div class="page flex-col">
			<div class="section_1 flex-row">
				<img class="label_1" @click="goBack" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">服务消息</span>
			</div>
		<div class="box_2 flex-col">
			<div v-for="(message, index) in messages" :key="index">
				<!-- 动态显示消息时间 -->
				<div class="text-wrapper_1 flex-row"><span class="text_2">{{ message.sendTime }}</span></div>
				<div class="group_1 flex-row justify-between">
					<img class="image_1" referrerpolicy="no-referrer"
						src="./assets/img/MasterDDSSlicePNG4e41f4022a8ae91c13bfdc7e9dd13cdb.png" />
					<div class="text-wrapper_2 flex-col">
						<!-- 动态显示消息内容 -->
						<span class="text_3">{{ message.messageContent }}</span>
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
						messageContent: "挂号成功！您已成功预约本周五上午10:00-11:00时段本院口腔科6室（田莉医生）。当前您排第16号，请合理规划时间，准时就诊。",
					},
					{
						sendTime: "下午02:40",
						messageContent: "您好，距您预约的看诊时间已不足一小时。请您提前于本周五上午10:00至本院口腔科6室（田莉医生）前等待就诊。",
					},
					// 你可以继续添加更多模拟数据
				];
				// 如果没有消息，跳转到指定的空界面
				if (this.messages.length === 0) {
					this.redirectToEmptyPage();
				}

				// 更新数据
				this.messages = dataFromBackend;
			},
			redirectToEmptyPage() {
				// 使用window.location.href进行页面跳转
				window.location.href = "lanhu_messagecenterservicemessagesempty";
			},
		},
		mounted() {
			// 当组件挂载时，调用获取数据的方法
			this.fetchMessages();
		},
	};
</script>

/*import axios from 'axios';

export default {
data() {
return {
messages: [],
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
axios.get('/api/messages')
.then(response => {
this.messages = response.data;
})
.catch(error => {
console.error("There was an error fetching the messages:", error);
});
// 如果没有消息，跳转到指定的空界面
if (this.messages.length === 0) {
this.redirectToEmptyPage();
}
},
redirectToEmptyPage() {
// 使用window.location.href进行页面跳转
window.location.href = "lanhu_messagecenterservicemessagesempty";
},
},
mounted() {
this.fetchMessages();
},
};
*/


<style scoped lang="css" src="./assets/index.rem.css" />