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
      <div class="text-wrapper_1 flex-col"><span class="text_2">贫血 、 休克</span></div>
      <span class="text_3">医疗知识</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          贫血的症状
        <br>
          贫血是指人体外周血红细胞容量减少，低于正常范围下限的一种常见的临床症状。比较常见的贫血的症状，总体有以下：
        <br>
          1. 一般表现：疲乏、困倦、软弱无力，是贫血中最常见和最早出现的症状。严重时部分病人可出现低热。
        <br>
          2. 呼吸系统：气急或呼吸困难，大都是由于呼吸中枢低氧或高碳酸血症所致。
        <br>
          3. 心血管系统：心悸、气短最为常见。患者有心跳加快，在心尖或肺动脉瓣区可听到柔和的收缩期杂音，严重贫血可听到舒张期杂音。严重贫血或原有冠心病，可引起心绞痛、心脏扩大、心力衰竭。
        <br>
          4. 消化系统：食欲减退、腹部胀气、恶心、便秘等为最多见的症状。
        <br>
          5. 生殖系统：妇女患者中常有月经失调，如闭经或月经过多。在男女两性中性欲减退均多见。
        <br>
          6. 中枢神经系统：头晕、头痛、耳鸣、眼花、注意力不集中、嗜睡等均为常见症状。严重贫血可出现晕厥，老年患者可有神志模糊及精神异常的表现。
        <br>
          7. 泌尿系统：贫血严重者可有轻度蛋白尿及尿浓缩功能减低，表现为夜间多尿。
        <br>
          8. 其他（但不限于）：皮肤干燥、毛发枯干、创口愈合较慢。
          <br />
        </span>

      </div>
    </div>
    <div class="block_2 flex-col">
      <span class="text_4">贫血</span>
      <div class="box_1 flex-row justify-between">
        <div class="text-wrapper_3 flex-col"><span class="text_5">贫血 </span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">休克</span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5">惨白 </span></div>
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