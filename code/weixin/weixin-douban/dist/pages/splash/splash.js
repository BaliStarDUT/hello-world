'use strict';

// 获取全局应用程序实例对象
var Promise = require('../../utils/bluebird');
var app = getApp();

// 创建页面实例对象
Page({
  /**
   * 页面的初始数据
   */
  data: {
    movies: [],
    loading: true
  },

  getCache: function getCache() {
    return new Promise(function (resolve) {
      app.wechat.getStorage('last_splash_data').then(function (res) {
        // 有缓存，判断是否过期
        if (res.data.expires < Date.now()) {
          // 已经过期
          console.log('storage expired');
          return resolve(null);
        }
        return resolve(res.data);
      }).catch(function (e) {
        return resolve(null);
      });
    });
  },
  handleStart: function handleStart() {
    // TODO: 访问历史的问题
    wx.switchTab({
      url: '../board/board'
    });
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function onLoad() {
    var _this = this;

    this.getCache().then(function (cache) {
      if (cache) {
        return _this.setData({ movies: cache.movies, loading: false });
      }

      app.douban.find('coming_soon', 1, 3).then(function (d) {
        _this.setData({ movies: d.subjects, loading: false });
        return app.wechat.setStorage('last_splash_data', {
          movies: d.subjects,
          expires: Date.now() + 1 * 24 * 60 * 60 * 1000
        });
      }).then(function () {
        return console.log('storage last splash data');
      });
    });

    // app.wechat.getStorage('last_splash_data')
    //   .then(res => {
    //     if (res.data.expires < Date.now()) {
    //       return console.log('storage expired')
    //     }
    //     return res.data.movies
    //   })
    //   .catch(e => {
    //     console.log('get storage faild')
    //     return app.douban.find('coming_soon', 1, 1).then(d => d.subjects)
    //   })
    //   .then(movies => {
    //     if (movies) return movies
    //     return app.douban.find('coming_soon', 1, 1).then(d => d.subjects)
    //   })
    //   .then(movies => {
    //     this.setData({ movies, loading: false })
    //     return app.wechat.setStorage('last_splash_data', {
    //       movies: movies,
    //       expires: Date.now() + 1 * 24 * 60 * 60 * 1000
    //     })
    //   })
    //   .then(() => {
    //     console.log('storage last splash data')
    //   })
    //   .catch(e => {
    //     console.error(e)
    //     this.setData({ loading: false })
    //   })
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function onReady() {
    // TODO: onReady
  },


  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function onShow() {
    // TODO: onShow
  },


  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function onHide() {
    // TODO: onHide
  },


  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function onUnload() {
    // TODO: onUnload
  },


  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function onPullDownRefresh() {
    // TODO: onPullDownRefresh
  }
});
//# sourceMappingURL=splash.js.map
