import dayjs from 'dayjs';
import { IVideoPost } from 'app/shared/model/video-post.model';
import { ChangeType } from 'app/shared/model/enumerations/change-type.model';
import { UrlType } from 'app/shared/model/enumerations/url-type.model';

export interface IVideoPostChangeHistory {
  id?: number;
  changeType?: keyof typeof ChangeType | null;
  oldtitle?: string | null;
  oldDescription?: string | null;
  oldUrl?: string | null;
  oldUrlType?: keyof typeof UrlType | null;
  oldIsAIGenerated?: boolean | null;
  oldIsPremium?: boolean | null;
  oldIsBlocked?: boolean | null;
  oldIsModerated?: boolean | null;
  oldIsActive?: boolean | null;
  oldCreatedBy?: string | null;
  oldCreatedOn?: dayjs.Dayjs | null;
  oldUpdatedBy?: string | null;
  oldUpdatedOn?: dayjs.Dayjs | null;
  post?: IVideoPost | null;
}

export const defaultValue: Readonly<IVideoPostChangeHistory> = {
  oldIsAIGenerated: false,
  oldIsPremium: false,
  oldIsBlocked: false,
  oldIsModerated: false,
  oldIsActive: false,
};
