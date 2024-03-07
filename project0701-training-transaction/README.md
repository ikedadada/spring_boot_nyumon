== ハンズオン
=== 概要
Spring を使用して簡単にトランザクション制御ができることを確認します。

=== 手順
. TrainingApplication クラスを実行してみましょう。main メソッドの最後の方で業務ロジックの reserve メソッドが呼び出されています。コンソール画面をみると、特にトランザクション制御されているようなログは出力されていません。

. ReservationServiceImpl クラスに、トランザクション制御するためのアノテーションをつけて、もう一度 TrainingApplication クラスを実行してみましょう。トランザクション制御の開始を意味する「Creating new transaction with name・・・ 」や、トランザクションのコミットを意味する「Committing JDBC transaction on Connection・・・」というログが出力されるはずです。

. デバッガのブレークポイントを使用して、main メソッドの途中で処理を止めて、「reservationService」変数の具象クラスの名前を調べてみましょう(変数の上にマウスカーソルを合わせると表示されます)。「$Proxy..」という名前になってれば、Spring が自動生成した Proxy になってる証拠です。
