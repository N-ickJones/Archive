procedure Delete(Value: Data) is
	Q, P, T, R, S: NodePtr;
	Check: Boolean;
begin
	FindIterative(Value);
	if Find_Ptr /= Null then
		P := Find_P;
		Q := Find_Ptr;

		if P.LLink = Q then
			Check := True;
		else
			Check := False;
		end if;

		T := Q;
		if T.RTag = False then
			Q := T.LLink;
			Free(T);


		else
			if T.LTag = False then
				Q := T.RLink;
				Free(T);
			else
				R := T.RLink;
				if R.LTag = False then
					R.LLink := T.LLink;
					Q := R;
					Free(T);
				else
					S := R.LLink;
					While S.LTag = True loop
						R := S;
						S := R.LLink;
					end loop;
					S.LLink := T.LLink; S.LTag := T.LTag;
					R.LLink := S.RLink; R.LTag := S.RTag;
					S.RLink := T.RLink; S.RTag := T.
					Q := S;
					Free(T);
				end if;
			end if;
		end if;

		if P = Wrap then
			Wrap := Q;
		else
			if Check = True then
				P.LLink := Q;
			else
				P.RLink := Q;
			end if;
		end if;

	else
		put(fOut, "Unable to Find & Delete: ");
		put(Value);
	end if;
end;



if T.RTag = False then
	if Check = True then
		-- Find Prececcesor
		Z := InOrderPredecessor(Q);
		Q := T.LLink;
		P.LLink := Q;
		P.LTag := T.LTag;
		if Z /= Q then -- or wrap
			Z.RLink := P;
		end if;
		Free(T);
	else
		-- Find Successor
		Z := InOrderSuccessor(Q);
		Q := T.RLink;
		P.RLink := Q;
		P.RTag := T.RTag;
		if Z /= Q then -- might not be neccessary
			Z.LLink := P;
		end if;
		Free(T);
	end if;
else
	if T.LTag = False then
		if Check = True then
			Null;
		else
			Q := T.RLink;
			P.LLink := Q;
			Q.RLink := P;
			Free(T);
		end if;

	else
		R := T.RLink;
		if R.LTag = False then
			R.LLink := T.LLink;
			Q := R;
			Free(T);
		else
			S := R.LLink;
			While S.LTag = True loop
				R := S;
				S := R.LLink;
			end loop;
			S.LLink := T.LLink; S.LTag := T.LTag;
			R.LLink := S.RLink; R.LTag := S.RTag;
			S.RLink := T.RLink; S.RTag := T.
			Q := S;
			Free(T);
		end if;
	end if;
end if;
