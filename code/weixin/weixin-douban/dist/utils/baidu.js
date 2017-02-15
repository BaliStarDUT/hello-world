'use strict';

var URI = 'https://api.map.baidu.com';
var fetch = require('./fetch');

function fetchApi(type, params) {
  return fetch(URI, type, params);
}

/**
 * 根据经纬度获取城市
 * @param  {Number} latitude   经度
 * @param  {Number} longitude  纬度
 * @return {Promise}       包含抓取任务的Promise
 */
function getCityName() {
  var latitude = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 39.90403;
  var longitude = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 116.407526;

  var params = { location: latitude + ',' + longitude, output: 'json', ak: 'B61195334f65b9e4d02ae75d24fa2c53' };
  return fetchApi('geocoder/v2/', params).then(function (res) {
    return res.data.result.addressComponent.city;
  });
}

module.exports = { getCityName: getCityName };
//# sourceMappingURL=baidu.js.map
