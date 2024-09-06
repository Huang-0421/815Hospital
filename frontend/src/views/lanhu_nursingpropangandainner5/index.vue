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
      <span class="text_1">护理对象</span>
      <div class="text-wrapper_1 flex-col"><span class="text_2">内科手术结束患者</span></div>
      <span class="text_3">护理方法</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          1. 避免伤口摩擦和外伤<br />
          不要在切口处穿紧身衣服。一般建议在伤口未愈合前，最好避免进行一些可能拉扯伤口范围部位的运动。这样才能降低伤口裂开或被拉扯的风险。<br />
          
          2. 定时更换敷料<br />
          根据伤口类型不同，换药的方式也不同，切记不要偷懒！请根据医生的指示来更换敷料，用干净、干燥的敷料敷盖在您的伤口/切口。<br />
          
          3. 尽量不要浸泡伤口<br />
          淋浴优于泡澡，尽量不要浸泡伤口，这样做会软化疤痕组织，并可能导致伤口/切口重新打开。淋浴时，您可以轻轻喷洒在伤口部位进行清洁，但不要摩擦伤口部位，因为这会引起疼痛并延迟愈合过程。<br />
          
          4. 选用防水敷料<br />
          不妨选用一些防水敷料外层，洗澡或淋浴时可以进行替换，能防止水分侵入，这样就可以安心淋浴或洗澡。否则在洗澡前一定要去除、更换不防水的敷料。<br />
          
          5. 淋浴后，应尽量保持伤口干燥<br />
          在重新敷上敷料之前，将切口风干或用干净的纱布垫或干净的洗澡巾轻轻拍干伤口。不要大力擦洗或摩擦伤口。您也可以使用抗菌药膏来保护切口免受感染。<br />
          
          6. 不要使用化学物品<br />
          一般建议不要使用含有刺激性的皮肤清洁剂、酒精、过氧化物、碘或含有抗菌化学物质的肥皂等等。这些有可能会损坏伤口组织，并减慢愈合速度。<br />
          
          7. 不要涂抹化妆品<br />
          如果在脸部动手术，避免化妆品对于脸部伤口的刺激，一般建议等伤口完全愈合之后方可进行日常化妆。<br />
          
          8. 均衡饮食<br />
          一般建议选择均衡饮食，适当增加高蛋白的摄取量，有助于增强皮肤的体质，有助于伤口的愈合。<br />
          
          9. 如果你吸烟，请戒烟！<br />
          吸烟不利于体内的血液循环，最终不利于伤口自行愈合。<br />
          
          10. 好好防晒<br />
          无论是伤口还是手术切口，都会在皮肤上留下一道疤痕。这些刚愈合的伤口疤痕很娇嫩，对阳光很敏感，如果被紫外线照射，很容易发生色素沉淀，疤痕表层可能会变黑。一般来说，手术后的前6个月伤口需要防晒，首选物理防晒，用衣物或胶布贴遮挡疤痕，或者等伤口较为稳定后选择涂抹防晒霜的化学防晒方式。不过，有些疤痕会随着时间渐渐消失，有些则不会；适当使用乳液和皮肤柔软剂，有助于淡化、软化疤痕。<br />
          
          如果看到伤口出现以下变化，请立即咨询您的医生：<br />
          - 伤口更红、更肿或更热<br />
          - 伤口出现绿色或黄色分泌物<br />
          - 伤口伴随着难闻的气味<br />
          - 伤口加压后流血不止<br />
          - 疼痛感加剧<br />
          - 伤口周围感觉硬硬或饱胀感<br />
          - 伤口/切口裂开<br />
          - 发烧超过 37.8°C<br />
        </span>

      </div>
    </div>

    <div class="block_2 flex-col">
      <span class="text_4">内科手术</span>
      <div class="box_1 flex-row justify-between">

        <div class="text-wrapper_3 flex-col"><span class="text_5">内科手术</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">息肉</span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5">肿瘤</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">心脏</span></div>
        
      </div>
    </div>
  </div>
</template>

<script>
export default {

  methods: {
    goBack() {
      // 这里留出链接供你填写
      this.$router.push('/lanhu_nursingpropaganda'); 
    }
  }
};
</script>


<style scoped lang="css" src="./assets/index.rem.css"></style> <!-- 外部样式文件 -->