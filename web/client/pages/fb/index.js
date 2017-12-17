// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    facebookList: [],
    imgSrc1: "http://wx1.sinaimg.cn/mw1024/65a92dc9gy1flz63wbjy5j20qo0zkguz.jpg"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.request({
      url: 'https://gem.dragon-yuan.me/api/getFacebook', //仅为示例，并非真实的接口地址
      method: "POST",
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      complete: function (res) {
        if (res == null || res.data == null) {
           reject(new Error('网络请求失败'))
        }
      },
      success: function (res) {
        that.setData({
          facebookList: res.data.result.list
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})