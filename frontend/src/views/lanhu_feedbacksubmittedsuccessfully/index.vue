<template>
	<div class="page flex-col">
		<div class="box_1 flex-col">
			<div class="nav-bar_1 flex-row">
				<img class="icon_1" @click="goBack" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">提交成功</span>
			</div>
			<div class="image-wrapper_1 flex-col">
				<img class="icon_2" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNGf4a1a00c5585473b596900104b9783af.png" />
			</div>
			<div class="text-group_1 flex-col justify-between">
				<span class="text_2">提交成功，我们将尽快查看！</span>
				<span class="text_3">感谢您的反馈，您的满意是我们最大的目标</span>
			</div>
			<button class="button_1 flex-col" @click="goIndex()"><span class="text_4">返回首页</span></button>
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
			goIndex() {
				this.$router.push('/lanhu_index');
			},
			goBack() {
				this.$router.go(-1);
			}
		}
	};
</script>
<style scoped lang="css" src="./assets/index.rem.css" />