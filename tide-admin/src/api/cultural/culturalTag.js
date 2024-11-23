import request from '@/utils/request'

// 查询文化标签列表
export function listCulturalTag(query) {
  return request({
    url: '/cultural/culturalTag/list',
    method: 'get',
    params: query
  })
}

// 查询文化标签详细
export function getCulturalTag(id) {
  return request({
    url: '/cultural/culturalTag/' + id,
    method: 'get'
  })
}

// 新增文化标签
export function addCulturalTag(data) {
  return request({
    url: '/cultural/culturalTag',
    method: 'post',
    data: data
  })
}

// 修改文化标签
export function updateCulturalTag(data) {
  return request({
    url: '/cultural/culturalTag',
    method: 'put',
    data: data
  })
}

// 删除文化标签
export function delCulturalTag(id) {
  return request({
    url: '/cultural/culturalTag/' + id,
    method: 'delete'
  })
}
