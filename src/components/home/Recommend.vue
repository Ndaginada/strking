<template>
        <div>
            <div>
                <mt-swipe :auto="4000" style="height: 200px">
                    <mt-swipe-item>
                        <img src="../../images/lunbo1.png" class="carousel_img">
                    </mt-swipe-item>
                    <mt-swipe-item>
                        <img src="../../images/lunbo2.png" class="carousel_img">
                    </mt-swipe-item>
                    <mt-swipe-item>
                        <img src="../../images/lunbo3.png" class="carousel_img">
                    </mt-swipe-item>
                </mt-swipe>
            </div>
            <br/>

            <iframe src="//player.bilibili.com/player.html?aid=16125552&cid=26315534&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"
            style="width: 100%;height: 180px">
            </iframe>
            <h4 style="text-align: center;margin: 0.2rem">iPhone X一周体验：真不如买8Plus</h4>

            <iframe src="//player.bilibili.com/player.html?aid=16125552&cid=26315534&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"
                    style="width: 100%;height: 180px;margin-top: 1rem">
            </iframe>
            <h4 style="text-align: center;margin: 0.2rem">iPhone X一周体验：真不如买8Plus</h4>
        </div>
</template>

<style scoped lang="less">
    @import "../../less/my-recommend";
</style>

<script>
    export default {
        name: 'Recommend',

        data() {
            return {}
        },

        props: {},

        components: {},

        mounted() {
            this.$nextTick(() => {
            });
        },

        watch: {},

        computed: {},

        filters: {},

        methods: {}
    }
</script>