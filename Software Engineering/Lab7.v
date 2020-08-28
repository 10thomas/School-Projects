Require Import NaturalDeduction.
Require Import Hoare.
Require Import Lemmas.

Lemma Lab7_1 (r i n m : nat) :
{{ True }} 
  r ::= 0;;
  i ::= 0;;
  While i <? n
  Do
    r ::= r+m;;
    i ::= i+1
  Od
{{ r = n*m }}.
Proof.
Admitted.

Lemma Lab7_2 (r s i n : nat) :
{{ True }}
  r ::= 0;;
  s ::= 2;;
  i ::= 0;;
  While i <? n 
  Do
    r ::= r+s;;
    s ::= s+2;;
    i ::= i+1
  Od
{{ r = n*(n+1) }}.
Proof.
Admitted.

Lemma Lab7_3 (r i n : nat) :
{{ 1 <= n }}
  r ::= 0;;
  i ::= 1;;
  While 2*i <=? n
  Do
    r ::= r+1;;
    i ::= 2*i
  Od
{{ 2^r <= n /\ n < 2^(r+1) }}.
Proof.
Admitted.

Lemma Lab7_4 (r i n : nat) :
{{ True }}
  i ::= 1;;
  r ::= n;;
  While negb (i =? n)
  Do
    i ::= i+1;;
    r ::= r+n
  Od
{{ r = n*n }}.
Proof.
Admitted.

Lemma Lab7_5 (r i n : nat) :
{{ True }}
  i ::= n;;
  r ::= 0;;
  While 0 <? i
  Do
    i ::= i-1;;
    r ::= r+n
  Od
{{ r = n*n }}.
Proof.
Admitted.
