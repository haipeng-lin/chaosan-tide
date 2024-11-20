import request from '@/utils/request'

// 查询文化专题列表
export function listCulturalTopic(query) {
  return request({
    url: '/cultural/culturalTopic/list',
    method: 'get',
    params: query
  })
}

// 查询文化专题详细
export function getCulturalTopic(id) {
  return request({
    url: '/cultural/culturalTopic/' + id,
    method: 'get'
  })
}

// 新增文化专题
export function addCulturalTopic(data) {
  return request({
    url: '/cultural/culturalTopic',
    method: 'post',
    data: data
  })
}

// 修改文化专题
export function updateCulturalTopic(data) {
  return request({
    url: '/cultural/culturalTopic',
    method: 'put',
    data: data
  })
}

// 删除文化专题
export function delCulturalTopic(id) {
  return request({
    url: '/cultural/culturalTopic/' + id,
    method: 'delete'
  })
}
