<template>
    <div class="page">
        <!-- <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage"  :page-sizes="[100, 200, 300, 400]" :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"  :total="400">
        </el-pagination> -->
        <el-pagination 
                @current-change="getPagination"
                :current-page="currentPage"  
                :page-size="pageSize" 
                layout="total,  prev, pager, next, jumper"  
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
export default {
    props:{
        pageSize:{
          type:Number,
          default:10
        },
        total:{
          type:Number,
          default:100
        }
    },
    data() {
      return {
        currentPage: 1,
      };
    },
    methods: {
      getPagination(current){
        this.$emit('getPagination',current)
      }
    }
    
}
</script>

<style>
.page{
    margin-top: 10px;
    background: #fff;
    padding: 10px;
}
</style>