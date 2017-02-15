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
    boards: [{ key: 'in_theaters' }, { key: 'coming_soon' }, { key: 'top250' }
    // { key: 'weekly' },
    // { key: 'new_movies' },
    // { key: 'us_box', name: '北美票房榜' }
    ],
    loading: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function onLoad() {
    var _this = this;

    var promises = this.data.boards.map(function (board) {
      return app.douban.find(board.key, 1, 10).then(function (d) {
        board.title = d.title;
        board.movies = d.subjects;
        return board;
      });
    });
    Promise.all(promises).then(function (boards) {
      return _this.setData({ boards: boards, loading: false });
    });
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
//# sourceMappingURL=board.js.map
