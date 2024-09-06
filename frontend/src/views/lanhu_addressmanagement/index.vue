<template>
	<div class="page flex-col">
		<div class="group_1 flex-col">
			<div class="box_1 flex-row">
				<img class="label_1" referrerpolicy="no-referrer" @click="goBack"
					src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
				<span class="text_1">地址管理</span>
				<span class="text_2" @click="goToManagement">管理</span>
			</div>
		</div>
		<div class="group_2 flex-col">
			<div class="group_3 flex-row justify-between" @click="showPopup = true">
				<img class="label_2" referrerpolicy="no-referrer"
					src="./assets/img/MasterDDSSlicePNGf2b713d4043938c3279efb39628c68e4.png" />
				<span class="text_3">添加地址</span>
			</div>
			<div class="group_4 flex-col"></div>
			<div v-if="addresses.length >0" class="group_5 flex-col justify-between">
				<!-- 使用v-for指令遍历地址数据 -->
				<div v-for="(address, index) in addresses" :key="index" class="box_2 flex-col justify-end">
					<span class="text_4">{{ address.shortAddress }}</span>
					<div class="group_6 flex-row justify-between">
						<div class="box_3 flex-col justify-between">
							<span class="text_5">{{ address.detailedAddress }}</span>
							<div class="group_7 flex-row">
								<span class="text_6">{{ address.recipientName }}</span>
								<span class="text_7">{{ address.recipientPhone }}</span>
								<div v-if="address.isDefault === 1 || address.tag" class="text-wrapper_1 flex-col">
									<span class="text_8">{{ address.isDefault === 1 ? '默认' : address.tag }}</span>
								</div>
							</div>
						</div>
						<img class="label_3" referrerpolicy="no-referrer" @click="editAddress(index)"
							src="./assets/img/MasterDDSSlicePNG357db931aedccd11f62a4d34841a664a.png" />
					</div>
					<div class="group_8 flex-col"></div>
				</div>
			</div>
			<van-empty v-else
				src="/views/lanhu_addressmanagement/assets/img/MasterDDSSlicePNG022ad212eeed463a7935340cc439cfe5.png"
				image-size="80" description="暂无地址" />


		</div>
		<van-popup v-model="showPopup" position="bottom" :style="{ height: '65%' }">
			<div class="popup-content">
				<van-field v-model="newAddress.region" label="所在地区" placeholder="请输入所在地区" />
				<van-field v-model="newAddress.detail" label="详细地址与门牌号" placeholder="请输入详细地址" />
				<van-field v-model="newAddress.name" label="收货人姓名" placeholder="请输入收货人姓名" />
				<van-field v-model="newAddress.phone" label="联系电话" placeholder="请输入联系电话" />

				<div class="address-tags">
					<span v-for="tag in tags" :key="tag" class="tag" :class="{ active: newAddress.tag === tag }"
						@click="toggleTagSelection(tag)">{{ tag }}</span>
				</div>

				<van-button class="savebtn" size="small" @click="saveAddress">保存地址</van-button>
			</div>
		</van-popup>
	</div>
</template>

<script>
	import {
		Popup,
		Field,
		Button,
		Empty
	} from 'vant';
	import axios from 'axios';

	export default {
		components: {
			'van-popup': Popup,
			'van-field': Field,
			'van-button': Button,
			'van-empty': Empty,
		},
		data() {
			return {
				// 假设从后端获取的地址数据存储在addresses数组中
				addresses: [{
						shortAddress: "四川省 广元市 利州区 万缘街道",
						detailedAddress: "白龙路与万龙路交叉口西北80米老街社区三期",
						recipientName: "王钟强",
						recipientPhone: "13350492788",
						tag: "公司",
						isDefault: 1, // 新增属性
					},
					{
						shortAddress: "四川省 广元市 利州区 万缘街道",
						detailedAddress: "白龙路与万龙路交叉口西北80米老街社区三期",
						recipientName: "张三",
						recipientPhone: "13350490000",
						tag: "家",
						isDefault: 0,
					},
					// 你可以在这里添加更多的地址对象
				],
				showPopup: false, // 控制弹出层的显示与隐藏
				editingIndex: null,


				newAddress: {
					region: '',
					detail: '',
					name: '',
					phone: '',
					tag: '',
				},
				tags: ['家', '公司', '学校', '父母', '朋友', '自定义'],

			};
		},
		methods: {
			goBack() {
				this.$router.push('/lanhu_personalcenter'); // 跳转到lanhu_personalcenter/index.vue
			},
			goToManagement() {
				this.$router.push('/lanhu_addressmanagementmanage'); // 跳转到lanhu_addressmanagementmanage/index.vue
			},
			toggleTagSelection(tag) {
				if (this.newAddress.tag === tag) {
					// 如果当前选中的标签再次被点击，取消选中
					this.newAddress.tag = '';
				} else {
					// 否则将其设为选中标签
					this.newAddress.tag = tag;
				}
			},
			async fetchData() {
				/*try {
					const response = await axios.get('/api/addresses'); // 请求后端获取地址数据
					this.addresses = response.data; // 假设后端返回的数据为地址数组
				} catch (error) {
					console.error('获取地址数据失败:', error);
				}*/
			},
			editAddress(index) {
				const address = this.addresses[index];
				this.newAddress = {
					region: address.shortAddress,
					detail: address.detailedAddress,
					name: address.recipientName,
					phone: address.recipientPhone,
					tag: address.tag || '',
				};
				this.editingIndex = index; // 保存当前正在编辑的地址索引
				this.showPopup = true; // 打开弹出层以进行编辑
			},
			async saveAddress() {
				if (this.newAddress.region && this.newAddress.detail && this.newAddress.name && this.newAddress
					.phone) {
					const addressData = {
						shortAddress: this.newAddress.region,
						detailedAddress: this.newAddress.detail,
						recipientName: this.newAddress.name,
						recipientPhone: this.newAddress.phone,
						tag: this.newAddress.tag || '',
					};

					try {
						if (this.editingIndex !== null) {
							// 编辑模式
							const addressId = this.addresses[this.editingIndex].id; // 假设每个地址有唯一ID
							await axios.put(`/api/addresses/${addressId}`, addressData);
							this.addresses.splice(this.editingIndex, 1, addressData); // 更新本地地址数据
						} else {
							// 新增模式
							const response = await axios.post('/api/addresses', addressData);
							this.addresses.push(response.data); // 假设后端返回新添加的地址
						}

						this.showPopup = false; // 保存后关闭弹出层
						this.clearNewAddress();
						this.editingIndex = null; // 重置编辑索引
					} catch (error) {
						console.error('保存地址失败:', error);
					}
				} else {
					alert('请填写完整的地址信息！');
				}
			},
			async deleteAddress(index) {
				const addressId = this.addresses[index].id; // 假设每个地址有唯一ID
				try {
					await axios.delete(`/api/addresses/${addressId}`);
					this.addresses.splice(index, 1); // 从本地数据中删除
				} catch (error) {
					console.error('删除地址失败:', error);
				}
			},
			clearNewAddress() {
				this.newAddress = {
					region: '',
					detail: '',
					name: '',
					phone: '',
					tag: '',
				};
			},
		},
		created() {
			this.fetchData(); // 组件创建时获取地址数据
		},
	};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />