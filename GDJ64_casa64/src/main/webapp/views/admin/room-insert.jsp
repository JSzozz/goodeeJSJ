<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-4">
                                        <h2 class="text-center">객실추가</h2>
                                    </div>
                                    <div class="col-12">
                                      <form action="#" class="mt-5">
                                        <!-- 객실명 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="roomName" class="col-sm-1 col-form-label text-center">객실명</label>
                                          <div class="col-sm-4">
                                            <input type="text" id="roomName" class="form-control" placeholder="객실명을 입력해주세요.">
                                          </div>
                                        </div>
                                        <!-- 객실정보 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="roomInfo" class="col-sm-1 col-form-label text-center">객실소개</label>
                                          <div class="col-sm-11">
                                            <textarea id="roomInfo" class="form-control" rows="4" placeholder="객실정보를 입력해주세요."></textarea>
                                          </div>
                                        </div>
                                        <!-- 인원 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="capacity" class="col-sm-1 col-form-label text-center">기준인원</label>
                                          <div class="col-sm-2">
                                            <input type="number" id="capacity" class="form-control" placeholder="기준인원">
                                          </div>
                                          <label for="maxCapcity" class="col-sm-1 col-form-label text-center">최대인원</label>
                                          <div class="col-sm-2">
                                            <input type="number" id="maxCapcity" class="form-control" placeholder="최대인원">
                                          </div>
                                        </div>
                                        <!-- 가격 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="price" class="col-sm-1 col-form-label text-center">가격</label>
                                          <div class="col-sm-3">
                                            <input type="number" id="price" class="form-control" placeholder="가격을 입력해주세요.">
                                          </div>
                                        </div>
                                        <!-- 옵션 -->                                            
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="option" class="col-sm-1 col-form-label text-center">옵션</label>
                                          <div class="col-sm-2">
                                            <button type="button" id="option" class="btn btn-dark">옵션설정</button>
                                          </div>
                                        </div>
                                        <!-- 객실 사진 -->                                            
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="attachment" class="col-sm-1 col-form-label text-center">객실사진</label>
                                          <div class="col-sm-4">
                                            <input type="file" id="attachment" class="form-control" multiple>
                                          </div>
                                        </div>
                                        <!-- 객실 공개 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label class="col-sm-1 col-form-label text-center">공개설정</label>
                                          <div class="col-sm-11 d-flex align-items-center">
                                            <div class="form-check form-check-inline">
                                              <input class="form-check-input" type="radio" name="room-status" id="public" value="public" />
                                              <label class="form-check-label" for="public">공개</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                              <input class="form-check-input" type="radio" name="room-status" id="private" value="private" />
                                              <label class="form-check-label" for="private">비공개</label>
                                            </div>
                                          </div>
                                        </div>
                                      </form>
                                    </div>
                                    <!-- 관리자 버튼 -->
                                    <div class="d-flex justify-content-end mt-2">
                                        <form action="#">
                                            <button type="submit" class="btn btn-dark">객실추가</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<%@ include file="/views/admin/common/footer.jsp"%>