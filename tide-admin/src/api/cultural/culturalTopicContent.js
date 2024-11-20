import request from '@/utils/request'

// 查询文化专题内容列表
export function listCulturalTopicContent(query) {
  return request({
    url: '/cultural/culturalTopicContent/list',
    method: 'get',
    params: query
  })
}

// 查询文化专题内容详细
export function getCulturalTopicContent(id) {
  return request({
    url: '/cultural/culturalTopicContent/' + id,
    method: 'get'
  })
}

// 新增文化专题内容
export function addCulturalTopicContent(data) {
  return request({
    url: '/cultural/culturalTopicContent',
    method: 'post',
    data: data
  })
}

// 修改文化专题内容
export function updateCulturalTopicContent(data) {
  return request({
    url: '/cultural/culturalTopicContent',
    method: 'put',
    data: data
  })
}

// 删除文化专题内容
export function delCulturalTopicContent(id) {
  return request({
    url: '/cultural/culturalTopicContent/' + id,
    method: 'delete'
  })
}
