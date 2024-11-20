import request from '@/utils/request'

// 查询节日类别列表
export function listCulturalCategory(query) {
  return request({
    url: '/cultural/culturalCategory/list',
    method: 'get',
    params: query
  })
}

// 查询节日类别详细
export function getCulturalCategory(id) {
  return request({
    url: '/cultural/culturalCategory/' + id,
    method: 'get'
  })
}

// 新增节日类别
export function addCulturalCategory(data) {
  return request({
    url: '/cultural/culturalCategory',
    method: 'post',
    data: data
  })
}

// 修改节日类别
export function updateCulturalCategory(data) {
  return request({
    url: '/cultural/culturalCategory',
    method: 'put',
    data: data
  })
}

// 删除节日类别
export function delCulturalCategory(id) {
  return request({
    url: '/cultural/culturalCategory/' + id,
    method: 'delete'
  })
}
