<template>
  <div class="page ">
    <!-- 左上角返回按钮 -->
    <div class="image-wrapper_1  back-button" >
      <button class="button_innernursing" @click="goBack">
        <img
          class="image_1"
          referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNGb3ea18f7a9df8b31682748391d9a6e76.png"
        />
      </button>
      
    </div>

    <div class="block_1 flex-col">
      <span class="text_1">医疗对象</span>
      <div class="text-wrapper_1 flex-col"><span class="text_2"> 消化不良 、 吞咽困难 、 恶心呕吐 </span></div>
      <span class="text_3">医疗知识</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          消化不良是上腹部的疼痛或不适感。消化不良的感觉可能被描述为不消化、胀气、饱腹感或烧灼感。在少量进食后即发生饱胀感（早饱），进食正常餐量后感到饱胀感（餐后饱胀）或与进餐无关。有关重度腹部不适的更多信息，请参阅急性腹痛专题。
        <br>
          因为消化不良通常是模糊的、轻微的不适，很多人不会就诊，直到其持续了（反复）很长时间。有时突发消化不良，感觉显著（急性）。
        <br>
          其他症状包括食欲缺乏、反胃、便秘、腹泻、嗳气以及肠鸣。对有些人而言，进食会使症状加重。对其他人而言，进食可缓解症状。
        <br><br>
          消化不良的病因
          引起消化不良的原因很多，但普遍使用的术语“消化不良”不涉及食物消化问题。
        <br>
          消化不良可迅速发生于下列情况后
        <br>
          摄取大量食物
        <br>
          饮酒
        <br>
          某些刺激性药物（如二磷酸盐、红霉素、铁或非甾体抗炎药[NSAIDs]）
        <br>
          一些心脏病或不稳定心绞痛（冠状动脉缺血）发作患者只有消化不良的感觉而非胸痛。
        <br>
          反复发作性消化不良的常见病因包括
        <br>
          <br>胃癌或食管癌
        <br>
          胃的（胃）排空延迟
        <br>
          药品
        <br>
          胃食管反流病 (GERD)
        <br>
          胃炎或消化性溃疡病

          <br>幽门螺杆菌感染
        
        <br>  不明原因（无溃疡性消化不良）
        
        <br>  胃排空延迟是食物在胃停留时间异常延长。胃排空延迟常由影响到消化道神经的疾病（如糖尿病、结蹄组织疾病或神经系统疾病）引起。
        
         <br> 焦虑本身不能引起消化不良。但是，焦虑有时增加患者对不寻常或不愉快感觉的关注度，从而使消化不良恶化，因此轻微的不适变得非常痛苦。
        
         <br> 在许多患者中，医生在体格检查期间或者使用柔性观察导管查看食管或胃（上消化道内镜检查）之后或者进行影像学检查或实验室检测之后，没有发现异常。这类情况称为非溃疡性消化不良（功能性消化不良），患者出现症状可能由于对胃内感觉或肠道收缩的敏感性增加所致。
          <br />
        </span>

      </div>
    </div>
    <div class="block_2 flex-col">
      <span class="text_4">肚子痛胃痛</span>
      <div class="box_1 flex-row justify-between">
        <div class="text-wrapper_3 flex-col"><span class="text_5"> 消化不良  </span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">吞咽困难 </span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5"> 恶心呕吐 </span></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      // 这里留出链接供你填写
      this.$router.push('/lanhu_medicalknowledge'); 
    }
  }
};
</script>
<style scoped lang="css" src="./assets/index.rem.css" />

<style scoped lang="css" src="./assets/index.rem.css"></style> <!-- 外部样式文件 -->