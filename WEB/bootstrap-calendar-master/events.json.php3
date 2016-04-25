<?php
$my_db = new mysqli("localhost",아이디,비밀번호,테이블이름);
$res =mysqli_query($my_db,"SELECT * FROM Calendar");
 
$out = array();
while($data = mysqli_fetch_array($res)) {
    $out[] = array(
        'id' => $data['id'],
        'title' => $data['name'],
        'url' => $data['url'],
        'start' => strtotime($data['datetime']).'000',
        'end' => strtotime($data['datetime_end']).'000',
        'class' => $data['class']
 
);
}
echo json_encode(array('success' => 1, 'result' => $out));
exit;
?>