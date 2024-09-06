<template>
	<div class="page flex-col">
		<div class="group_1 flex-col">
			<div class="nav-bar_1 flex-row">
				<img class="icon_1" referrerpolicy="no-referrer" @click="goBack"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">反馈记录</span>
			</div>
			<div class="box_1 flex-col"></div>
		</div>
		<div class="group_2 flex-col">
			<div class="image-wrapper_1 flex-col">
				<img class="image_1" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG19e33ee9b6d195a70a44a867c71bc635.png" />
			</div>
			<span class="text_2">还没有反馈记录</span>
		</div>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				constants: {}
			};
		},
		methods: {
			goBack() {
				this.$router.push('/lanhu_feedback');
		}}
	};
</script>
<style scoped lang="css" src="./assets/index.rem.css" />