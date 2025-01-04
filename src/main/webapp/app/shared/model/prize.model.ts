import dayjs from 'dayjs';
import { ICompetition } from 'app/shared/model/competition.model';
import { PrizeType } from 'app/shared/model/enumerations/prize-type.model';
import { PrizeValueType } from 'app/shared/model/enumerations/prize-value-type.model';

export interface IPrize {
  id?: number;
  prizeType?: keyof typeof PrizeType | null;
  prizeTag?: string | null;
  prizeDetails?: string | null;
  prizeValueType?: keyof typeof PrizeValueType | null;
  prizeValue?: number | null;
  countOfPossibleWinners?: number | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  competition?: ICompetition | null;
}

export const defaultValue: Readonly<IPrize> = {
  isActive: false,
};
