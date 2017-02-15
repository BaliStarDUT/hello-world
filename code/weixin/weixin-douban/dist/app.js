'use strict';

/**
 * WeChat API 模块
 * @type {Object}
 * 用于将微信官方`API`封装为`Promise`方式
 * > 小程序支持以`CommonJS`规范组织代码结构
 */
var wechat = require('./utils/wechat.js');

/**
 * Douban API 模块
 * @type {Object}
 */
var douban = require('./utils/douban.js');

/**
 * Baidu API 模块
 * @type {Object}
 */
var baidu = require('./utils/baidu.js');

App({
  /**
   * Global shared
   * 可以定义任何成员，用于在整个应用中共享
   */
  data: {
    name: 'Douban Movie',
    version: '0.1.0',
    currentCity: '北京'
  },

  /**
   * WeChat API
   */
  wechat: wechat,

  /**
   * Douban API
   */
  douban: douban,

  /**
   * Baidu API
   */
  baidu: baidu,

  /**
   * 生命周期函数--监听小程序初始化
   * 当小程序初始化完成时，会触发 onLaunch（全局只触发一次）
   */
  onLaunch: function onLaunch() {
    var _this = this;

    wechat.getLocation().then(function (res) {
      var latitude = res.latitude,
          longitude = res.longitude;

      return baidu.getCityName(latitude, longitude);
    }).then(function (name) {
      _this.data.currentCity = name.replace('市', '');
      console.log('currentCity : ' + _this.data.currentCity);
    }).catch(function (err) {
      _this.data.currentCity = '北京';
      console.error(err);
    });
    console.log(' ========== Application is launched ========== ');
  },

  /**
   * 生命周期函数--监听小程序显示
   * 当小程序启动，或从后台进入前台显示，会触发 onShow
   */
  onShow: function onShow() {
    console.log(' ========== Application is showed ========== ');
  },

  /**
   * 生命周期函数--监听小程序隐藏
   * 当小程序从前台进入后台，会触发 onHide
   */
  onHide: function onHide() {
    console.log(' ========== Application is hid ========== ');
  }
});
//# sourceMappingURL=app.js.map
