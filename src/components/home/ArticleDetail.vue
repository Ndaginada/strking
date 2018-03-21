<template>
    <div>
        <mt-cell>
            <mt-header fixed title="Strking" class="heard">
                <router-link to="/home" slot="left">
                    <mt-button icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </mt-cell>

        <div class="title">
            <h1>开发中。。。</h1>
            <span>2017-2-1 </span>
            <span>——XXX</span>
        </div>
        <div class="context">
            <p>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</p>
        </div>
        <hr>
        <div class="revert">
            <div class="revert_detail">
                <mt-cell :title="titles" style="border: 3em black">
                    <img slot="icon" src="../../images/home_active.png" width="50" height="50">
                    <span>2017-3-8</span>
                </mt-cell>
                <p style="text-indent: 8%;font-size: medium">ssssssssss</p>
                <div @click="click_zan()" v-if="!is_zan">
                    <img src="../../images/zan.png"  width="20" height="20" style="margin-left: 80%"/>
                    <span>{{num_zan}}</span>
                </div>
                <div v-else>
                    <img src="../../images/zan_active.png"  width="20" height="20" style="margin-left: 80%"/>
                    <span>{{num_zan}}</span>
                </div>
                <hr>
            </div>
        </div>

    </div>
</template>

<script>

    import MtCell from "../../../node_modules/mint-ui/packages/cell/src/cell.vue";

    export default {
        name: 'ArticleDetail',

        data() {
            return {
                list: [1, 2, 3, 4, 5, 6],
                titles:'aa',
                is_zan:'',
                num_zan:0
            }
        },

        props: {},

        components: {MtCell},

        watch: {},

        computed: {

        },

        filters: {},

        methods: {
            click_zan(){
                this.is_zan = true;
                this.num_zan++;
                console.log("XXXXXXX")
            }
        }
    }
</script>

<style scoped lang="less">
    @import "../../less/my-article-detail";
</style>