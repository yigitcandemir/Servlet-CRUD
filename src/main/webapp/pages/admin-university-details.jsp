<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Üniversite Detayları</title></head>
<body>

<h2>Üniversite Bilgisi</h2>
<form action="EntityManagement" method="post">
    <input type="hidden" name="entity" value="university" />
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${university.id}" />
    Adı: <input type="text" name="name" value="${university.name}" /><br/>
    Website: <input type="text" name="website" value="${university.website}" /><br/>
    <button type="submit">Güncelle</button>
</form>

<form action="EntityManagement" method="post" onsubmit="return confirm('Silinsin mi?');">
    <input type="hidden" name="entity" value="university" />
    <input type="hidden" name="action" value="delete" />
    <input type="hidden" name="id" value="${university.id}" />
    <button type="submit" style="color:red;">Sil</button>
</form>

<hr/>

<h3>Yerleşkeler, Fakülteler ve Bölümler</h3>
<c:forEach var="campus" items="${campuses}">
    <div style="border:1px solid gray; padding:10px; margin-bottom:20px;">
        <h4>Yerleşke: ${campus.name}</h4>
        <p>Şehir: ${campus.city} | İlçe: ${campus.district} | Adres: ${campus.address}</p>

        <form action="EntityManagement" method="post">
            <input type="hidden" name="entity" value="campus" />
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="id" value="${campus.id}" />
            <input type="hidden" name="universityId" value="${university.id}" />
            Adı: <input type="text" name="name" value="${campus.name}" />
            Şehir: <input type="text" name="city" value="${campus.city}" />
            İlçe: <input type="text" name="district" value="${campus.district}" />
            Adres: <input type="text" name="address" value="${campus.address}" />
            <button type="submit">Güncelle</button>
        </form>

        <form action="EntityManagement" method="post" onsubmit="return confirm('Silinsin mi?');">
            <input type="hidden" name="entity" value="campus" />
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="${campus.id}" />
            <input type="hidden" name="universityId" value="${university.id}" />
            <button type="submit" style="color:red;">Sil</button>
        </form>

        <h5>Fakülteler</h5>
        <c:forEach var="faculty" items="${campus.faculties}">
            <div style="margin-left:20px; padding:5px; border-left:2px solid #aaa;">
                <strong>Fakülte: ${faculty.name}</strong>
                <form action="EntityManagement" method="post">
                    <input type="hidden" name="entity" value="faculty" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="id" value="${faculty.id}" />
                    <input type="hidden" name="universityId" value="${university.id}" />
                    <input type="hidden" name="campusId" value="${campus.id}" />
                    Adı: <input type="text" name="name" value="${faculty.name}" />
                    Telefon: <input type="text" name="telephone" value="${faculty.telephone}" />
                    Dekan: <input type="text" name="dean" value="${faculty.dean}" />
                    <button type="submit">Güncelle</button>
                </form>

                <form action="EntityManagement" method="post" onsubmit="return confirm('Silinsin mi?');" style="display:inline;">
                    <input type="hidden" name="entity" value="faculty" />
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="${faculty.id}" />
                    <input type="hidden" name="universityId" value="${university.id}" />
                    <input type="hidden" name="campusId" value="${campus.id}" />
                    <button type="submit" style="color:red;">Sil</button>
                </form>

                <ul>
                    <c:forEach var="department" items="${faculty.departments}">
                        <li>
                            <form action="EntityManagement" method="post" style="display:inline;">
                                <input type="hidden" name="entity" value="department" />
                                <input type="hidden" name="action" value="update" />
                                <input type="hidden" name="id" value="${department.id}" />
                                <input type="hidden" name="facultyId" value="${faculty.id}" />
                                <input type="hidden" name="universityId" value="${university.id}" />
                                <input type="text" name="name" value="${department.name}" />
                                <button type="submit">Güncelle</button>
                            </form>

                            <form action="EntityManagement" method="post" style="display:inline;" onsubmit="return confirm('Silinsin mi?');">
                                <input type="hidden" name="entity" value="department" />
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" name="id" value="${department.id}" />
                                <input type="hidden" name="facultyId" value="${faculty.id}" />
                                <input type="hidden" name="universityId" value="${university.id}" />
                                <button type="submit" style="color:red;">Sil</button>
                            </form>
                        </li>
                    </c:forEach>

                    <li>
                        <form action="EntityManagement" method="post">
                            <input type="hidden" name="entity" value="department" />
                            <input type="hidden" name="action" value="add" />
                            <input type="hidden" name="facultyId" value="${faculty.id}" />
                            <input type="hidden" name="universityId" value="${university.id}" />
                            <input type="text" name="name" placeholder="Yeni bölüm adı" />
                            <button type="submit">Ekle</button>
                        </form>
                    </li>
                </ul>
            </div>
        </c:forEach>
        <div style="margin-top:10px; padding:5px; background-color:#f9f9f9; border:1px dashed #ccc;">
    <h5>Yeni Fakülte Ekle</h5>
    <form action="EntityManagement" method="post">
        <input type="hidden" name="entity" value="faculty" />
        <input type="hidden" name="action" value="add" />
        <input type="hidden" name="universityId" value="${university.id}" />
        <input type="hidden" name="campusId" value="${campus.id}" />
        Adı: <input type="text" name="name" />
        Telefon: <input type="text" name="telephone" />
        Dekan: <input type="text" name="dean" />
        <button type="submit">Ekle</button>
    </form>
</div>

    </div>
</c:forEach>

<h4>Yeni Yerleşke Ekle</h4>
<form action="EntityManagement" method="post">
    <input type="hidden" name="entity" value="campus" />
    <input type="hidden" name="action" value="add" />
    <input type="hidden" name="universityId" value="${university.id}" />
    Adı: <input type="text" name="name" />
    Şehir: <input type="text" name="city" />
    İlçe: <input type="text" name="district" />
    Adres: <input type="text" name="address" />
    <button type="submit">Ekle</button>
</form>

</body>
</html>
