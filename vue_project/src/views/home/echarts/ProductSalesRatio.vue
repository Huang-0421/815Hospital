<template>
    <div>
        <div style="margin-top: 10px;">
            <div id="chart2" style="height:340px;"></div>
        </div>
    </div>
</template>

<script>
//import axios from 'axios'
import * as echarts from 'echarts';// eslint-disable-line no-unused-vars

export default({
    data(){
       return{
          nationNames:[],
          nationTotals:[],
          regionTotals:[]
       }
    },
    methods:{
        //绘制饼图方法
        pie(result){
        
        var myChart = echarts.init(document.getElementById('chart2'));
         // 绘制图表
         myChart.setOption({
            title: {  
                  text: '各科室人数占比图',  
                  left: 'center',  
                  fontSize: 20  
              },  
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                name: '各科室人数占比图',
                type: 'pie',
                radius: '50%',
                data: [
        { value: 52, name: '内科' },
        { value: 200, name: '外科' },
        { value:334, name: '妇科' },
        { value: 390, name: '儿科' },
        { value: 330, name: '骨科' }
      ],
                emphasis: {
                    itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
                }
            ]
            });
       }
    },
    mounted(){
        console.log('mounted-id',document.getElementById("charts"));
       // this.getNationTotals();
       // this.getRegionTotals();
       // this.getYearTotal();
       this.pie();
    },
    
})

</script>

<style>

</style>
