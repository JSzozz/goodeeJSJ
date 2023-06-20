<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-2">
                                        <h2 class="text-center">회원리스트</h2>
                                        <div id="select-line" class="float-end">
                                            <form>
                                                <div class="search-area d-flex justify-content-end mt-2">
                                                    <select name="notice-search">
                                                        <option value="user-name">이름</option>
                                                        <option value="user-nickname">닉네임</option>
                                                        <option value="user-email">이메일</option>
                                                        <option value="user-phone">전화번호</option>
                                                    </select>
                                                    <input type="text" name="keyword" placeholder="검색어를 입력해 주세요" class="ms-1">
                                                    <button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <th>선택</th>
                                            <th>No</th>
                                            <th>이름</th>
                                            <th>닉네임</th>
                                            <th>이메일</th>
                                            <th>연락처</th>
                                            <th>가입날짜</th>
                                        </thead>
                                        <tbody class="align-middle">
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>1</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>2</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>3</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>4</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>5</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>6</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>7</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>8</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>9</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice">
                                                </td>
                                                <td>10</td>
                                                <td>이동제</td>
                                                <td>동제동이</td>
                                                <td>@naver.com</td>
                                                <td>010-0000-0000</td>
                                                <td>23/06/19</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <!-- 관리자 버튼 -->
                                    <div class="d-flex justify-content-end">
                                        <button type="button" class="btn btn-dark me-2">블랙처리</button>
                                        <button type="button" class="btn btn-danger">회원탈퇴</button>
                                    </div>
                                    <!-- 페이지바 -->
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center">
                                            <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                            </li>
                                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </section>
<%@ include file="/views/admin/common/footer.jsp"%>