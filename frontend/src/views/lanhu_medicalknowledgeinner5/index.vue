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
      <div class="text-wrapper_1 flex-col"><span class="text_2">便血 、 黄疸 、 排尿异常 </span></div>
      <span class="text_3">医疗知识</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          便血原因
        <br>
          儿童出现便血原因，多由直肠息肉引起，血色鲜红、无痛、血与大便不混合。
        <br>
          成年人出现粘液状血便，并伴下腹部疼痛、便频等症状，一般多是溃疡性结肠炎引起的症状。
        <br>
          引起便血的九大疾病：
        <br>
          1. 痔核或肛裂、肛瘘
          血色一般为鲜红，且与粪便不相混，也不含有黏液，表现为大便后滴鲜血，尤在硬结大便时更易发生。
        <br>
          2. 细菌性痢疾
          大便常为脓血样，每次量不多，常伴有里急后重感；慢性期为间断性发作的黏液、脓血便。
        <br>
          3. 阿米巴痢疾
          大便多呈果酱样，或呈暗红色，量较多，常伴有脓性黏液，患者多有发热、腹胀、腹痛及里急后重表现。
        <br>
          4. 血吸虫病
          有疫水接触史，常表现为慢性腹泻，大便呈脓血样或为大便带血。
        <br>
          5. 溃疡性结肠炎
          大便一般为黏液脓血样便，重者可为血水样便。
        <br>
          6. 肠套叠
          排出黏液血便，常不含大便。
        <br>
          7. 直肠癌
        <br>
          8. 结肠癌
          大便变细，粪便伴有黏液脓血时应怀疑结肠癌的可能。
        <br>
          9. 直肠、结肠息肉
          直肠、乙状结肠或降结肠息肉时，表现为大便外附有新鲜血液。
        <br>
          这些人群要警惕便血：
        <br>
          年轻人请警惕大便出血。直肠恶变病人已经趋向年轻化，年龄小于30岁的患者所占比例已经从10年前的8%上涨至15%。
        <br>
          中老年人便血更莫大意。随着年龄的增长，人体中乙状结肠与直肠的恶变性病变（包括良性与恶性）明显增多。
          <br />
        </span>

      </div>
    </div>
    <div class="block_2 flex-col">
      <span class="text_4">排泄异常</span>
      <div class="box_1 flex-row justify-between">
        <div class="text-wrapper_3 flex-col"><span class="text_5">便血</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">尿黄</span></div>
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