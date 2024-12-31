<?php

$hostname = "localhost";
$database = "dbstudent";
$username = "root";
$password = "";

try {
    $db = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo json_encode(['message' => 'Connection failed: ' . $e->getMessage()]);
    exit();
}
if (isset($_REQUEST['selectFn']) && $_REQUEST['selectFn'] == "fnSaveData") {


    // $varName = $_REQUEST["stud_name"];
    // $varGender = $_REQUEST["stud_gender"];
    // $varDob = $_REQUEST["stud_dob"];
    // $varStudNo = $_REQUEST["stud_no"];
    // $varState = $_REQUEST["stud_state"];

    $varName = $_REQUEST["fullname"];
    $varGender = $_REQUEST["gender"];
    $varDob = $_REQUEST["birthdate"];
    $varStudNo = $_REQUEST["studNo"];
    $varState = $_REQUEST["state"];

    try {
        //$stmt = $db->prepare("INSERT INTO srudents (studName, studGender, studDob, studNo, studState) VALUES (:studName, :studGender, :studDob, :studNo, :studState)");
        $stmt = $db->prepare("INSERT INTO srudents (stud_name, stud_no, stud_state, stud_gender, stud_dob) VALUES (:studName, :studNo, :studGender,   :studState, :studDob)");

        // $stmt->execute(array(':stud_name' => $varName, ':stud_no' => $varStudNo, ':stud_gender' => $varGender, ':stud_dob' => $varDob, ':stud_state' => $varState));
        //$stmt->execute([':stud_name' => $varName, ':stud_no' => $varStudNo, ':stud_gender' => $varGender, ':stud_dob' => $varDob, ':stud_state' => $varState]);

        //$stmt = $db->prepare("INSERT INTO srudents (studName, studGender, studDob, studNo, studState) VALUES (:studName, :studGender, :studDob, :studNo, :studState)");

        $stmt->execute([
            ':studName' => $varName,
            ':studNo' => $varStudNo,
            ':studGender' => $varGender,
            ':studState' => $varState,
            ':studDob' => $varDob,

        ]);


        $response['respond'] = "Information Saved";
        echo json_encode($response);
    } catch (PDOException $e) {
        $response['respond'] = "Exception occured!" . $e->getMessage();
        echo json_encode($response);
    }
}

if (isset($_REQUEST['selectFn']) && $_REQUEST['selectFn'] == "fnSearchStudent") {
    $studID = $_REQUEST["studID"];

    try {
        $stmt = $db->prepare("SELECT * FROM srudents WHERE stud_no = :studID");
        $stmt->execute([':studID' => $studID]);

        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        if ($result) {
            echo json_encode($result);
        } else {
            echo json_encode([]);
        }
    } catch (PDOException $e) {
        echo json_encode(['message' => 'Exception occurred: ' . $e->getMessage()]);
    }
}
