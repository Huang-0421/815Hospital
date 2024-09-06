<template>
  <div class="page ">
    <!-- 左上角返回按钮 -->
    <div class="image-wrapper_1  back-button">
      <button class="button_innernursing" @click="goBack">
        <img class="image_1" referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNGb3ea18f7a9df8b31682748391d9a6e76.png" />
      </button>

    </div>

    <div class="block_1 flex-col">
      <span class="text_1">医疗对象</span>
      <div class="text-wrapper_1 flex-col"><span class="text_2"> 呼吸困难 、 咳嗽 、 咳痰 、 咯血 </span></div>
      <span class="text_3">医疗知识</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          咯血系指喉以下呼吸道或肺组织出血，经口腔咳出。其病因繁多，可因支气管、肺部、心血管或全身性疾病 (按解剖部位分) 引起。按病因可分为：感染性疾病、肿瘤、支气管 - 肺和肺血管结构异常、血液病、免疫性疾病、肺损伤和物理因素等。如从发生频率高低来看，最常见的病因依次为：支气管扩张、肺结核、肺癌、肺脓肿等。此外，虽经详细检查，仍有 20% 的咯血者病因始终难以明确。询问病史时应详细了解
          <br>
          1. 咯血发生的急缓、咯血量、性状、有无咳痰、是初次还是多次、咯血前有无喉痒等。
          <br>
          2. 伴随症状：有无发热、胸痛、咳嗽、胸闷、出汗、恐惧、呼吸困难、心悸等，以及与月经的关系等。体格检查观察咯血的量、性质和颜色；病人的一般状态，特别是血压、脉搏、呼吸和心率；神志、皮肤颜色、有无贫血、出血点、皮下结节和杵壮指 (趾)、淋巴结大小；肺内呼吸音变化，有无罗音、心脏杂音、心律，肝脾大小，有无下肢水肿等。实验室检查及其他特殊检查
          <br>
          1. 三大常规：血红蛋白、红细胞计数、红细胞积压及其动态变化、白细胞计数及分类、血小板计数、尿检中有无红、白细胞，便有无潜血等。
          <br>
          2. 凝血功能：出血时间、凝血时间、凝血酶原时间、纤维蛋白原等。
          <br>
          3. 痰液检查：痰找抗酸杆菌、肿瘤细胞、寄生虫卵、真菌等, 痰细菌培养。
          <br>
          4. X 线检查：胸部后前位及侧位摄影、必要时进行高分辨率计算机体层 X 线摄影 (HRCT) 检查。
          <br>
          5. 纤维支气管镜检查：找到出血部位和明确病变性质或局部止血治疗。
          <br>
          6. 支气管动脉造影：如怀疑支气管动脉出血 (如支气管扩张等)，为了明确出血部位和进行治疗，可考虑此项检查。
          <br>
          7. 肺动脉造影：怀疑肺动脉出血如肺栓塞、肺动静脉瘘可考虑此项检查。
          <br>
          8. 其他：超声心动图、骨髓检查、免疫系统检查等。
        </span>

      </div>
    </div>
    <div class="block_2 flex-col">
      <span class="text_4">呼吸不畅困难</span>
      <div class="box_1 flex-row justify-between">
        <div class="text-wrapper_4 flex-col"><span class="text_6">咳嗽</span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5">哮喘</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">咳痰</span></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
      goBack() {
        this.$router.push('/lanhu_medicalknowledge');
      }
    }
  };
</script>
<style scoped lang="css" src="./assets/index.rem.css" />
