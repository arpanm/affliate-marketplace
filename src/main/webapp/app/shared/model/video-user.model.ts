import dayjs from 'dayjs';
import { IBankDetails } from 'app/shared/model/bank-details.model';
import { ISponsor } from 'app/shared/model/sponsor.model';
import { VideoUserType } from 'app/shared/model/enumerations/video-user-type.model';

export interface IVideoUser {
  id?: number;
  userId?: string | null;
  userName?: string | null;
  name?: string | null;
  phone?: number | null;
  email?: string | null;
  description?: string | null;
  imageUrl?: string | null;
  userType?: keyof typeof VideoUserType | null;
  isBlocked?: boolean | null;
  blockedTill?: dayjs.Dayjs | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  bank?: IBankDetails | null;
  company?: ISponsor | null;
}

export const defaultValue: Readonly<IVideoUser> = {
  isBlocked: false,
  isActive: false,
};
