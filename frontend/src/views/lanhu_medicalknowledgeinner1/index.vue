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
      <div class="text-wrapper_1 flex-col"><span class="text_2">有发热 、 疼痛 、体重改变等症状的</span></div>
      <span class="text_3">医疗知识</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          发热即体温升高，这是为应对感染，机体的恒温器（位于大脑中的下丘脑）在较高温度下重置而出现的。不是由温度设定点重置引起的体温升高，称为过热。
          <br>
          虽然正常体温被界定为37°C（98.6°F），但人的体温在一天之中是不断变化的。在清晨体温最低，傍晚体温最高—有时达到 99.9° F (37.7° C)。同样，发热时的体温也不会保持在一个温度。有时体温在每天达到高峰，然后又恢复正常。这种过程称为间歇热。或者，温度有所变化，但不会恢复正常。这种过程称为弛张热。医生不再认为发烧上升和下降的方式对某些疾病的诊断非常重要。
          <br>
          体温可通过身体的多个部位测量。最常见的部位是口腔和直肠。其他部位有耳、前额，最后是腋窝。通常使用数字式温度计测量体温。不再推荐使用含汞的玻璃温度计，因为它们可能破损而让人们接触到汞。
          <br>
          如口腔温度符合以下情况，则体温升高：
          <br>
          清晨温度高于 99°F (37°C)。
          <br>
          清晨过后的任何时间，温度都高于 100.4°F (38°C)。
          <br>
          温度高于此人已知的正常日常体温。
          <br>
          直肠和耳内温度比口腔温度高约 1.0°F (0.6°C)。
          <br>
          皮肤温度（例如前额）比口腔温度低约 1.0°F (0.6°C)。
          <br>
          很多人往往轻率的使用“发热”一词，往往意味着他们觉得太热，太冷，或出汗，但他们并没有实际测量其体温。
          <br>
          有些人（比如高龄者、极为年幼者和酒精使用障碍者）的发热能力下降。
          <br />
        </span>
      </div>
    </div>
    <div class="block_2 flex-col">
      <span class="text_4">发烧发热头痛</span>
      <div class="box_1 flex-row justify-between">
        <div class="text-wrapper_3 flex-col"><span class="text_5">发热</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">头痛</span></div>
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