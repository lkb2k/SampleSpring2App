<#-- @ftlvariable name="info" type="com.kgawa.spring2.model.PersonalInfo" -->

<html>
    <title>Personal Info of ${info.name}</title>
<head>
</head>
<body>
    <h2>Personal Information for ${info.name}</h2>

    <table>
        <tr>
            <td>Name</td>
            <td>${info.name}</td>
        </tr>
        <tr>
            <td>Company</td>
            <td>${info.companyName}</td>
        </tr>
        <tr>
            <td>Age</td>
            <td>${info.age}</td>
        </tr>
        <tr>
            <td>Employee Grade</td>
            <td>${info.employeeGrade}</td>
        </tr>
        <tr>
            <td>Location</td>
            <td>
                ${info.location.street1}<br/>
                <#if info.location.street?exists>
                    ${info.location.street2}<br/>
                </#if>
                ${info.location.city}<br/>
                ${info.location.state}<br/>
                ${info.location.zip}<br/>
            </td>
        </tr>
    </table>

    <p>
        This page has been viewed ${viewCount} time(s).
    </p>
</body>
</html>