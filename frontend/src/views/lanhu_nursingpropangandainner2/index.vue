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
      <div class="text-wrapper_1 flex-col"><span class="text_2">产后孕妇</span></div>
      <span class="text_3">护理方法</span>
      <div class="text-wrapper_2 flex-col">
       <span class="paragraph_1">
         1. 一般护理
       <br />
         （1）每日测体温、脉搏、呼吸、血压，如体温超过 38℃，应加强观察，并向医师报告。
       <br />
         （2）饮食：产后 1 小时可让产妇进食流食或清淡半流饮食，若哺乳，应多进蛋白质和汤汁食物，同时适当补充维生素和铁剂，推荐补充铁剂 3 个月。
       <br />
         （3）排尿与排便：产后 4 小时内鼓励产妇及时排尿，多饮水，多吃蔬菜和含纤维素的食物，以保持大便通畅。
       <br />
         （4）活动：经阴道分娩的产妇，产后 6-12 小时内即可起床轻微活动，于产后 2 日可在室内随意走动。行会阴侧切或剖宫产的产妇可适当推迟活动时间，鼓励产妇床上适当活动，预防下肢静脉血栓形成。产妇产后应避免负重劳动或蹲位活动，以防止子宫脱垂。产后 2 周宜做膝胸卧位，可预防或纠正子宫后倾。
       <br />
         2. 症状护理
       <br />
         （1）产后 2 小时护理：产后 2 小时内极易发生严重并发症，如产后出血、产后心衰、产后子痫和羊水栓塞等。产后应严密观察生命体征，子宫收缩情况及阴道出血量，注意子宫底高度及膀胱是否充盈。
       <br />
         （2）会阴及会阴伤口冲洗：用 0.05% 聚维酮碘擦洗外阴，每日 2-3 次；或用 2% 苯扎溴铵（新洁尔灭）冲洗或擦洗外阴。
       <br />
         （3）乳房护理：乳房保持清洁、干燥，适时擦洗。
       <br />
         3. 健康教育
       <br />
         （1）产后保健操：鼓励产妇做产后保健操，可预防尿失禁、膀胱直肠膨出及子宫脱垂。
       <br />
         （2）计划生育指导：产后 42 日内禁止性生活。根据产后恢复情况，恢复正常性生活，并指导产妇选择适当的避孕措施，一般哺乳者宜选用工具避孕，不哺乳者可选择药物避孕。
       <br />
         （3）产后检查：告知产妇于产后 42 日带孩子一起来医院进行全面检查。产后检查包括全身检查和妇科检查。
       
         <br />
       </span>

      </div>
    </div>

    <div class="block_2 flex-col">
      <span class="text_4">护理建议一</span>
      <div class="box_1 flex-row justify-between">

        <div class="text-wrapper_3 flex-col"><span class="text_5">孕妇</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">产后</span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5">生孩子</span></div>
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