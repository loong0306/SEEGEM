Page({
  /**
   * 页面的初始数据
   */
  data: {
    instagramList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    wx.request({
      url: 'https://gem.dragon-yuan.me/api/getInstagram',
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
        console.log('ins', res);
        that.setData({
          instagramList: res.data.result.list
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