program asdf;
  var
     q,n,m,w,p,i,a,b,c : longint;
     t		       : array[0..500] of longint;
     d,nu	       : array[0..500,0..500] of longint;
  procedure spfa;
    var
       head,tail,x,y,i : longint;
       dist,num	       : array[0..500] of longint;
       ff	       : array[0..500] of boolean;
       q	       : array[0..20000] of longint;
    begin
      fillchar(dist,sizeof(dist),60);
      fillchar(ff,sizeof(ff),0);
      fillchar(num,sizeof(num),0);
      head:=0;
      tail:=1;
      q[1]:=0;
      ff[0]:=true;
      num[0]:=1;
      dist[0]:=0;
      while head<>tail do
        begin
          head:=(head+1) mod 20000;
          x:=q[head];
          for i:=1 to t[x] do
            begin
              y:=nu[x,i];
              if d[x,i]+dist[x]<dist[y] then
                begin
                  dist[y]:=dist[x]+d[x,i];
                  if not ff[y] then
                    begin
                      tail:=(tail+1) mod 20000;
                      q[tail]:=y;
                      ff[y]:=true;
                      inc(num[y]);
                      if num[y]>n then
                        begin
                          writeln('YES');
                          exit;
                        end;
                    end;
                end;
            end;
          ff[x]:=false;
        end;
      writeln('NO');
    end;
  begin
    readln(q);
    for p:=1 to q do
      begin
        fillchar(t,sizeof(t),0);
        fillchar(d,sizeof(d),0);
        fillchar(nu,sizeof(nu),0);
        readln(n,m,w);
        t[0]:=n;
        for i:=1 to n do
          begin
            nu[0,i]:=i;
            d[0,i]:=100000;
          end;
        for i:=1 to m do
          begin
            readln(a,b,c);
            inc(t[a]);
            inc(t[b]);
            d[a,t[a]]:=c;
            nu[a,t[a]]:=b;
            d[b,t[b]]:=c;
            nu[b,t[b]]:=a;
          end;
        for i:=1 to w do
          begin
            readln(a,b,c);
            inc(t[a]);
            d[a,t[a]]:=-c;
            nu[a,t[a]]:=b;
          end;
        spfa;
      end;
  end.