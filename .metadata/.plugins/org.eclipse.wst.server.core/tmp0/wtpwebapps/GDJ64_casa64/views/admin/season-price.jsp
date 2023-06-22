<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-4">
                                        <h2 class="text-center">요금관리</h2>
                                    </div>
                                    <div class="col-12">
                                        <div class="row d-flex align-items-center">
                                            <div class="season-tab">
                                                <ul>
                                                    <li>
                                                        <a href="#" class="active">성수기</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">비수기</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>                                      
                                    </div>
                                    <div class="col-12">
                                      <form action="#" class="mt-3">
                                        <!-- 기간설정 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="roomInfo" class="col-sm-1 col-form-label text-center"><strong>적용기간</strong></label>
                                          <div class="col-sm-11">
                                            <button type="button" class="btn btn-dark">+ 기간추가</button>
                                          </div>
                                        </div>                                 
                                      </form>
                                      <!-- 테이블 -->
                                      <table class="table text-center mt-5">
                                        <thead>
                                            <th>객실명</th>
                                            <th>평일요금</th>
                                            <th>주말요금</th>
                                            <th>관리</th>
                                        </thead>
                                        <tbody class="align-middle">
                                            <tr>
                                                <td colspan="4">
                                                    <button type="button" class="btn btn-dark">+ 객실추가</button>
                                                </td>
                                            </tr>
                                        </tbody>
                                      </table>                                      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<%@ include file="/views/admin/common/footer.jsp"%>