import request from '@/utils/request'

// 查询节日列表
export function listCulturalFestival(query) {
  return request({
    url: '/cultural/culturalFestival/list',
    method: 'get',
    params: query
  })
}

// 查询节日详细
export function getCulturalFestival(id) {
  return request({
    url: '/cultural/culturalFestival/' + id,
    method: 'get'
  })
}

// 新增节日
export function addCulturalFestival(data) {
  return request({
    url: '/cultural/culturalFestival',
    method: 'post',
    data: data
  })
}

// 修改节日
export function updateCulturalFestival(data) {
  return request({
    url: '/cultural/culturalFestival',
    method: 'put',
    data: data
  })
}

// 删除节日
export function delCulturalFestival(id) {
  return request({
    url: '/cultural/culturalFestival/' + id,
    method: 'delete'
  })
}
